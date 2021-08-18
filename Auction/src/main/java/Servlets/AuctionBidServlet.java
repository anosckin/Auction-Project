package Servlets;

import DAO.*;
import Helper.GeneralConstants;
import Helper.Hasher;
import Models.*;
import Services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuctionBidServlet extends HttpServlet implements GeneralConstants {

    public AuctionBidServlet(){ super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Pages/auction-bid.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        SqlAuctionDAO auctionDAO = (SqlAuctionDAO) servletContext.getAttribute(SqlAuctionDAO.AUCTIONDAO_STR);
        SqlBidderAuctionDAO bidderAuctionDAO = (SqlBidderAuctionDAO) servletContext.getAttribute(SqlBidderAuctionDAO.ATTRIBUTE_NAME);

        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);

        String parse = request.getParameter("item-code");
        String newBidStr = request.getParameter("price");

        int itemCode=0,newBid=0;
        if (newBidStr != ""){
            newBid = Integer.parseInt(newBidStr);
        }
        if (parse != ""){
            itemCode = Integer.parseInt(parse);
        }

        Auction currentAuction = auctionDAO.getAuction(itemCode);
        if (currentAuction==null){
            request.setAttribute(MESSAGE_STRING, "Auction doesnt exist");
            request.getRequestDispatcher("Pages/auction-bid.jsp").forward(request, response);
            return;
        }
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        if (currentAuction.getEnd_date().compareTo(date)<0){
            request.setAttribute(MESSAGE_STRING, "Bid has already ended");
            request.getRequestDispatcher("Pages/auction-bid.jsp").forward(request, response);
            return;
        }

        int bidder = 0;
        if (currentAuction!=null){
            if (currentUser != null){
                bidder = currentUser.getId();
                if (currentUser.getId()==currentAuction.getSeller_id()){
                    request.setAttribute(MESSAGE_STRING, "You can't bet on your own auctions");
                    request.getRequestDispatcher("Pages/auction-bid.jsp").forward(request, response);
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
                            request.getRequestDispatcher("Pages/auction-bid.jsp").forward(request, response);
                        }
                    }
                }
            }
        }else{
            request.setAttribute(MESSAGE_STRING, "Item with given code does not exist.");
            request.getRequestDispatcher("Pages/auction-bid.jsp").forward(request, response);
        }
    }
}