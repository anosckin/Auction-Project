package Servlets;

import DAO.SqlAuctionDAO;
import DAO.SqlReviewsDao;
import DAO.UserDAO;
import Models.Auction;
import Models.Review;
import Models.User;
import Models.UserInfo;
import Services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static Helper.GeneralConstants.*;
import static Helper.GeneralConstants.MESSAGE_STRING;

public class AddAuctionServlet extends HttpServlet {

    public AddAuctionServlet(){ super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Pages/add-auction.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        SqlAuctionDAO auctionsDao = (SqlAuctionDAO) servletContext.getAttribute(SqlAuctionDAO.AUCTIONDAO_STR);

        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);

        String itemName = request.getParameter("item-name");

        if (itemName.equals("")){
            request.setAttribute(MESSAGE_STRING, "Please Enter Item Name");
            request.getRequestDispatcher("Pages/add-auction.jsp").forward(request, response);
            return;
        }

        String parse = request.getParameter("price");

        if (parse.equals("")){
            request.setAttribute(MESSAGE_STRING, "Please Enter The Price");
            request.getRequestDispatcher("Pages/add-auction.jsp").forward(request, response);
            return;
        }

        int price = 0;

        if (parse != ""){
            price = Integer.parseInt(parse);
        }

        if (price < 0){
            price=Math.abs(price);
        }

        parse = request.getParameter("min-increment");

        if (parse.equals("")){
            request.setAttribute(MESSAGE_STRING, "Please Enter The Minimum increment");
            request.getRequestDispatcher("Pages/add-auction.jsp").forward(request, response);
            return;
        }

        int minIncrement = 0;
        if (parse != ""){
            minIncrement = Integer.parseInt(parse);
        }
        if (minIncrement<1){
            request.setAttribute(MESSAGE_STRING, "Please Enter a Minimum Increment which is a positive number");
            request.getRequestDispatcher("Pages/add-auction.jsp").forward(request, response);
            return;
        }

        minIncrement = Math.max(minIncrement,1);

        String description = request.getParameter("comment");

        String endDateString = request.getParameter("end-date");
        if (endDateString.equals("")){
            request.setAttribute(MESSAGE_STRING, "Please Enter an End Date");
            request.getRequestDispatcher("Pages/add-auction.jsp").forward(request, response);
            return;
        }

        Date endDate = Date.valueOf(endDateString);

        int dealerId = -1;

        if (currentUser != null){
            dealerId = currentUser.getId();
        }

        Auction auction = new Auction(NO_ID,dealerId,dealerId,price,minIncrement,price,endDate,itemName,description);

        auctionsDao.insertAuction(auction);
        response.sendRedirect(request.getContextPath() + "/account-home");
    }
}

