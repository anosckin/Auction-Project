package Servlets;

import DAO.UserDAO;
import Services.UserService;
import Helper.GeneralConstants;
import Helper.Hasher;
import Models.User;
import Models.UserInfo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAccountServlet extends HttpServlet implements GeneralConstants {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Pages/create-account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        UserService userService = (UserService)servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("password-repeat");
        String firstName = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phonenumber");
        String note = request.getParameter("note");

        User foundUser = userDAO.getUser(username);

        if (foundUser != null) {
            request.setAttribute(MESSAGE_STRING, ERROR_ACCOUNT_EXISTS);
            request.getRequestDispatcher("Pages/create-account.jsp").forward(request, response);
        } else if (!password.equals(repeatedPassword)) {
            request.setAttribute(MESSAGE_STRING, ERROR_REPEATED_PASSWORD);
            request.getRequestDispatcher("Pages/create-account.jsp").forward(request, response);
        } else {
            UserInfo userInfo = new UserInfo(firstName, lastname, email, address, phoneNumber, note);

            String passwordHash = Hasher.hash(password);
            boolean inserted = userService.insertUser(username, passwordHash, userInfo);

            if (inserted) {
                request.setAttribute(MESSAGE_STRING, ACCOUNT_CREATED_SUCCESSFULLY);
                request.getRequestDispatcher("Pages/create-account.jsp").forward(request, response);
            } else {
                request.setAttribute(MESSAGE_STRING, ERROR_REGISTRATION);
                request.getRequestDispatcher("Pages/create-account.jsp").forward(request, response);
            }
        }
    }
}
