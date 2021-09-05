package Servlets;

import DAO.SqlAuctionDAO;
import DAO.SqlBidderAuctionDAO;
import DAO.UserDAO;
import Helper.SessionHelper;
import Models.Auction;
import Models.BidderAuction;
import Models.User;
import Services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static Helper.GeneralConstants.*;

@WebServlet(name = "MakeBidServlet", urlPatterns = {"/MakeBidServlet"})
public class MakeBidServlet extends HttpServlet {
    public MakeBidServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean userExists = SessionHelper.checkIfUserExists(session);
        User currentUser = (User) session.getAttribute(CURRENT_USER_STRING);

        if (!userExists) {
            response.sendRedirect("");
            return;
        }

        ServletContext servletContext = getServletContext();
        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();

        List<User> topUsers = userDAO.getAllUsers();
        request.setAttribute("users", topUsers);

        SqlAuctionDAO auctionDAO = (SqlAuctionDAO) servletContext.getAttribute(SqlAuctionDAO.AUCTIONDAO_STR);
        List<Auction> auctions = auctionDAO.getAllAuctions();
        request.setAttribute("auctions", auctions);

        SqlAuctionDAO auctionDao = (SqlAuctionDAO)servletContext.getAttribute(SqlAuctionDAO.AUCTIONDAO_STR);
        request.setAttribute("auctionsDAO", auctionDao);

        SqlBidderAuctionDAO bidderAuctionDAO = (SqlBidderAuctionDAO) servletContext.getAttribute(SqlBidderAuctionDAO.ATTRIBUTE_NAME);
        List<BidderAuction> bidderAuctions = bidderAuctionDAO.getBidderAuctionListByUser(currentUser.getId());
        request.setAttribute("interested-auctions", bidderAuctions);

        String priceParse = request.getParameter("price");
        String auctionIdParse = request.getParameter("auctionId");

        int auctionId = Integer.parseInt(auctionIdParse);

        if (priceParse.equals("")) {
            request.setAttribute(MESSAGE_STRING, "Please Enter a Price");
            if (request.getParameter("pageId").equals("active-auctions.jsp")) {
                request.getRequestDispatcher("Pages/active-auctions.jsp").forward(request, response);
            }
            if (request.getParameter("pageId").equals("interested-auctions.jsp")) {
                request.getRequestDispatcher("Pages/interested-auctions.jsp").forward(request, response);
            }
            return ;
        }

        int newBid = Integer.parseInt(priceParse);
        Auction currentAuction = auctionDAO.getAuction(auctionId);

        int bidder = 0;

        if (currentUser != null) {
            bidder = currentUser.getId();
            if (currentUser.getId() == currentAuction.getSeller_id()) {
                request.setAttribute(MESSAGE_STRING, "You can't bet on your own auctions");
                if (request.getParameter("pageId").equals("active-auctions.jsp")) {
                    request.getRequestDispatcher("Pages/active-auctions.jsp").forward(request, response);
                }
                if (request.getParameter("pageId").equals("interested-auctions.jsp")) {
                    request.getRequestDispatcher("Pages/interested-auctions.jsp").forward(request, response);
                }
            } else {
                if (currentAuction.getCurrent_price() + currentAuction.getMin_increment() <= newBid) {
                    auctionDAO.updateAuction(newBid, bidder, currentAuction.getId());
                    BidderAuction bidderAuction = new BidderAuction(bidder, currentAuction.getId(), newBid);

                    bidderAuctionDAO.removeBidderAuctionByUserAuction(bidder, currentAuction.getId());
                    bidderAuctionDAO.insertBidderAuction(bidderAuction);

                    response.sendRedirect(request.getContextPath() + "/account-home");
                } else {
                    if (currentAuction.getSeller_id() == currentAuction.getCurrent_bidder_id() // there is no current bidder
                            && currentAuction.getCurrent_price() <= newBid) {

                        auctionDAO.updateAuction(newBid, bidder, currentAuction.getId());
                        BidderAuction bidderAuction = new BidderAuction(bidder, currentAuction.getId(), newBid);

                        bidderAuctionDAO.removeBidderAuctionByUserAuction(bidder, currentAuction.getId());
                        bidderAuctionDAO.insertBidderAuction(bidderAuction);

                        response.sendRedirect(request.getContextPath() + "/account-home");
                    } else {
                        request.setAttribute(MESSAGE_STRING, "Bid was insufficient");
                        if (request.getParameter("pageId").equals("active-auctions.jsp")) {
                            request.getRequestDispatcher("Pages/active-auctions.jsp").forward(request, response);
                        }
                        if (request.getParameter("pageId").equals("interested-auctions.jsp")) {
                            request.getRequestDispatcher("Pages/interested-auctions.jsp").forward(request, response);
                        }
                    }
                }
            }
        }
    }
}
