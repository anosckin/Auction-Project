package Servlets;

import DAO.SqlAuctionDAO;
import DAO.SqlReviewsDao;
import DAO.UserDAO;
import Helper.SessionHelper;
import Models.Auction;
import Models.Review;
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

public class UserReviewsServlet  extends HttpServlet {


    public UserReviewsServlet(){ super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean userExists = SessionHelper.checkIfUserExists(session);

        if (!userExists) {
            response.sendRedirect("");
            return;
        }

        ServletContext servletContext = getServletContext();
        UserService userService = (UserService)servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();

        List<User> topUsers = userDAO.getAllUsers();
        request.setAttribute("users", topUsers);

        SqlReviewsDao reviewsDAO = (SqlReviewsDao)servletContext.getAttribute(SqlReviewsDao.ATTRIBUTE_NAME);
        List<Review> reviews = reviewsDAO.getAllReviews();
        request.setAttribute("reviews", reviews);

        request.getRequestDispatcher("Pages/user-reviews.jsp").forward(request, response);
    }

}
