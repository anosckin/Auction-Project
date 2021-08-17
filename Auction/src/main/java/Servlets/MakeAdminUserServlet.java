package Servlets;

import DAO.UserDAO;
import Helper.SessionHelper;
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

import static Helper.GeneralConstants.*;

@WebServlet(name = "MakeAdminUserServlet", urlPatterns = {"/MakeAdminUserServlet"})
public class MakeAdminUserServlet extends HttpServlet {
    public MakeAdminUserServlet(){ super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean userExists = SessionHelper.checkIfUserExists(session);
        User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);

        if (!userExists || !currentUser.getIsAdmin()) {
            response.sendRedirect("");
            return;
        }

        ServletContext servletContext = getServletContext();
        UserService userService = (UserService)servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();

        String username = request.getParameter("userToAdmin");
        User foundUser = userDAO.getUser(username);
        if (foundUser != null){
            userDAO.makeAdminUser(username);
            response.sendRedirect(request.getContextPath() + "/allusers");
        }else{
            request.setAttribute(MESSAGE_STRING, "User with given username does not exist.");
            request.getRequestDispatcher("Pages/all-users.jsp").forward(request, response);
        }
    }
}
