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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static Helper.GeneralConstants.CURRENT_USER_STRING;
import static Helper.GeneralConstants.USER_SERVICE;

public class InterestedAuctionsServlet extends HttpServlet {

    public InterestedAuctionsServlet(){super();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean userExists = SessionHelper.checkIfUserExists(session);

        if (!userExists) {
            response.sendRedirect("");
            return;
        }

        User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);

        ServletContext servletContext = getServletContext();

        SqlBidderAuctionDAO bidderAuctionDAO = (SqlBidderAuctionDAO)servletContext.getAttribute(SqlBidderAuctionDAO.ATTRIBUTE_NAME);
        List<BidderAuction> bidderAuctions = bidderAuctionDAO.getBidderAuctionListByUser(currentUser.getId());
        request.setAttribute("interested-auctions", bidderAuctions);

        SqlAuctionDAO auctionDao = (SqlAuctionDAO)servletContext.getAttribute(SqlAuctionDAO.AUCTIONDAO_STR);
        request.setAttribute("auctionDAO", auctionDao);

        request.getRequestDispatcher("Pages/interested-auctions.jsp").forward(request, response);
    }
}
