package Servlets;

import DAO.SqlAuctionDAO;
import DAO.SqlReviewsDao;
import DAO.UserDAO;
import Models.Auction;
import Models.Review;
import Models.User;
import Services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.List;

import static Helper.GeneralConstants.*;

@WebServlet(name = "WriteReviewServlet", urlPatterns = {"/WriteReviewServlet"})
public class WriteReviewServlet extends HttpServlet {

    public WriteReviewServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        UserService userService = (UserService)servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();

        SqlReviewsDao reviewsDao = (SqlReviewsDao) servletContext.getAttribute(SqlReviewsDao.ATTRIBUTE_NAME);

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(CURRENT_USER_STRING);


        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);

        SqlAuctionDAO auctionDAO = (SqlAuctionDAO)servletContext.getAttribute(SqlAuctionDAO.AUCTIONDAO_STR);
        List<Auction> auctions = auctionDAO.getAllAuctions();
        request.setAttribute("auctions", auctions);

        String star = request.getParameter("rate");

        if (star == null) {
            request.setAttribute(MESSAGE_STRING, "You have to choose a rating");
            request.getRequestDispatcher("Pages/user-auctions.jsp").forward(request, response);
            return;
        }
        int score = Integer.parseInt(star);

        String reviewText = request.getParameter("comment");

        String auctionIdParse = request.getParameter("auctionId");
        int auctionId = Integer.parseInt(auctionIdParse);
        Auction auction = auctionDAO.getAuction(auctionId);

        Review review = new Review(auctionId, currentUser.getId(), auction.getSeller_id(), score, reviewText);
        reviewsDao.insertReview(review);

        response.sendRedirect(request.getContextPath() + "/account-home");
    }
}
