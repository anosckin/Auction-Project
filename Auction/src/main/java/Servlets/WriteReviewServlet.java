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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static Helper.GeneralConstants.*;

public class WriteReviewServlet extends HttpServlet {

    public WriteReviewServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();

        SqlAuctionDAO auctionDAO = (SqlAuctionDAO) servletContext.getAttribute(SqlAuctionDAO.AUCTIONDAO_STR);
        List<Auction> auctions = auctionDAO.getAllAuctions();

        SqlReviewsDao reviewsDao = (SqlReviewsDao) servletContext.getAttribute(SqlReviewsDao.ATTRIBUTE_NAME);

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(CURRENT_USER_STRING);

        String itemIdParse = request.getParameter("item");

        if (itemIdParse.equals("")) {
            request.setAttribute(MESSAGE_STRING, "You Have to State an Item");
            request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
            return;
        }
        int itemId = Integer.parseInt(itemIdParse);
        User seller = null;
        Auction currentAuction = null;

        for (Auction auction : auctions) {
            if (auction.getId() == itemId && !auction.isActive()) {
                currentAuction = auction;
                seller = userDAO.getUser(auction.getSeller_id());
                if (auction.getCurrent_bidder_id() != currentUser.getId()) {
                    request.setAttribute(MESSAGE_STRING, "You have not won that auction");
                    request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
                    return;
                }
                break;
            }
        }

        if (currentAuction == null) {
            request.setAttribute(MESSAGE_STRING, "There is no finished auction with this ID");
            request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
            return;
        }

        SqlReviewsDao reviewDAO = (SqlReviewsDao) servletContext.getAttribute(SqlReviewsDao.ATTRIBUTE_NAME);

        List <Review> reviews = reviewDAO.getAllReviews();
        for (Review review : reviews){
            if (review.getItemId()==currentAuction.getId()){
                request.setAttribute(MESSAGE_STRING, "You have already rated this item");
                request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
                return;
            }
        }

        String parse = request.getParameter("point");

        if (parse.equals("")) {
            request.setAttribute(MESSAGE_STRING, "You Have to Give a Rating");
            request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
            return;
        }

        int score;
        score = Integer.parseInt(parse);

        if (score > 5 || score < 0) {
            request.setAttribute(MESSAGE_STRING, "You Have to Give a Rating between 0-5");
            request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
            return;
        }

        String reviewComment = request.getParameter("comment");
        int reviewerId = -1;

        if (currentUser != null) {
            reviewerId = currentUser.getId();
        }


        if (seller != null) {
            if (seller.getId() == reviewerId) {
                request.setAttribute(MESSAGE_STRING, "Can't Rate Yourself");
                request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
            } else {
                Review review = new Review(itemId, reviewerId, seller.getId(), score, reviewComment);
                reviewsDao.insertReview(review);
                response.sendRedirect(request.getContextPath() + "/account-home");
            }
        } else {
            // this shouldn't happen
            request.setAttribute(MESSAGE_STRING, "User with given username does not exist.");
            request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
        }
    }
}
