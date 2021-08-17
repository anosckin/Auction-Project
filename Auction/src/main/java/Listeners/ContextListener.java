package Listeners;

import DAO.*;
import Helper.GeneralConstants;
import Services.UserService;
import org.apache.commons.dbcp.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContextListener implements ServletContextListener, GeneralConstants {
    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ResourceBundle reader = null;

        try {
            // Reads resources from dbconfig.properties file located
            // under src/main/resources/
            reader = ResourceBundle.getBundle("dbconfig");

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(reader.getString("db.url"));
            dataSource.setUsername(reader.getString("db.username"));
            dataSource.setPassword(reader.getString("db.password"));
//            Uncomment for testing
//            System.out.println(reader.getString("db.url"));
//            System.out.println(reader.getString("db.username"));
//            System.out.println(reader.getString("db.password"));

            connection = dataSource.getConnection();

            UserDAO userDAO = new SqlUserDAO(connection);
            UserInfoDAO userInfoDAO = new SqlUserInfoDAO(connection);
            UserService userService = new UserService(userDAO, userInfoDAO, connection);

            SqlAuctionDAO sqlAuctionDAO = new SqlAuctionDAO(connection);
            SqlReviewsDao reviewsDao = new SqlReviewsDao(connection);

            ServletContext servletContext = servletContextEvent.getServletContext();
            servletContext.setAttribute(USER_SERVICE, userService);
            servletContext.setAttribute(SqlAuctionDAO.AUCTIONDAO_STR, sqlAuctionDAO);
            servletContext.setAttribute(SqlUserDAO.ATTRIBUTE_NAME, userDAO);
            servletContext.setAttribute(SqlReviewsDao.ATTRIBUTE_NAME, reviewsDao);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
