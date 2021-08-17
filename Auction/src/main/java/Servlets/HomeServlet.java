package Servlets;

import Helper.GeneralConstants;
import Helper.SessionHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomeServlet extends HttpServlet implements GeneralConstants {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        SessionHelper.initSession(session);

        request.getRequestDispatcher("Pages/home.jsp").forward(request, response);
    }
}
