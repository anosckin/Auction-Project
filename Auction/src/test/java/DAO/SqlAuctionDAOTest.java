package DAO;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.util.ResourceBundle;

public class SqlAuctionDAOTest {

    private SqlUserDAO sqlUserDAO;
    private UserInfoDAO userInfoDAO;
    private Connection connection;

    public SqlAuctionDAOTest() {
        ResourceBundle reader = null;

        try {
            // Reads resources from dbconfig.properties file located
            // under src/main/resources/
            reader = ResourceBundle.getBundle("dbconfig");

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(reader.getString("db.url"));
            dataSource.setUsername(reader.getString("db.username"));
            dataSource.setPassword(reader.getString("db.password"));

            connection = dataSource.getConnection();

            sqlUserDAO = new SqlUserDAO(connection);
            userInfoDAO = new SqlUserInfoDAO(connection);

            sqlUserDAO.deleteEverything();
            userInfoDAO.deleteEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
