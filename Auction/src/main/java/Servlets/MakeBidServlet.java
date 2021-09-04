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
    public MakeBidServlet(){ super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean userExists = SessionHelper.checkIfUserExists(session);
        User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);

        if (!userExists) {
            response.sendRedirect("");
            return;
        }

        ServletContext servletContext = getServletContext();
        SqlAuctionDAO auctionDAO = (SqlAuctionDAO)  servletContext.getAttribute(SqlAuctionDAO.AUCTIONDAO_STR);
        SqlBidderAuctionDAO bidderAuctionDAO = (SqlBidderAuctionDAO) servletContext.getAttribute(SqlBidderAuctionDAO.ATTRIBUTE_NAME);

        String priceParse = request.getParameter("price");
        String auctionIdParse = request.getParameter("auctionId");

        int auctionId = Integer.parseInt(auctionIdParse);

        if (priceParse.equals("")){
            request.setAttribute(MESSAGE_STRING, "Please Enter a Price");
            request.getRequestDispatcher("Pages/active-auctions.jsp").forward(request, response);
        }

        int newBid = Integer.parseInt(priceParse);
        Auction currentAuction = auctionDAO.getAuction(auctionId);

        int bidder = 0;

        UserService userService = (UserService)servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();

        List<User> topUsers = userDAO.getAllUsers();
        request.setAttribute("users", topUsers);

        List<Auction> auctions = auctionDAO.getAllAuctions();
        request.setAttribute("auctions", auctions);

        if (currentUser != null){
            bidder = currentUser.getId();
            if (currentUser.getId() == currentAuction.getSeller_id()){
                request.setAttribute(MESSAGE_STRING, "You can't bet on your own auctions");
                request.getRequestDispatcher("Pages/active-auctions.jsp").forward(request, response);
            }else {
                if (currentAuction.getCurrent_price() + currentAuction.getMin_increment() <= newBid) {
                    auctionDAO.updateAuction(newBid, bidder, currentAuction.getId());
                    BidderAuction bidderAuction = new BidderAuction(bidder, currentAuction.getId(), newBid);

                    bidderAuctionDAO.removeBidderAuctionByUserAuction(bidder, currentAuction.getId());
                    bidderAuctionDAO.insertBidderAuction(bidderAuction);

                    response.sendRedirect(request.getContextPath() + "/account-home");
                } else {
                    if (currentAuction.getSeller_id() == currentAuction.getCurrent_bidder_id() // there is no current bidder
                            && currentAuction.getCurrent_price() <= newBid){

                        auctionDAO.updateAuction(newBid, bidder, currentAuction.getId());
                        BidderAuction bidderAuction = new BidderAuction(bidder, currentAuction.getId(), newBid);

                        bidderAuctionDAO.removeBidderAuctionByUserAuction(bidder, currentAuction.getId());
                        bidderAuctionDAO.insertBidderAuction(bidderAuction);

                        response.sendRedirect(request.getContextPath() + "/account-home");
                    }else {
                        request.setAttribute(MESSAGE_STRING, "Bid was insufficient");
                        request.getRequestDispatcher("Pages/active-auctions.jsp").forward(request, response);
                    }
                }
            }
        }
    }
}
