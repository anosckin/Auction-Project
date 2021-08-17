package DAO;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.util.ResourceBundle;
import Helper.SQLPK;
import Models.User;
import Models.UserInfo;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class SqlReviewsDAOTest {
    private SqlUserDAO sqlUserDAO;
    private UserInfoDAO userInfoDAO;
    private Connection connection;

    public SqlReviewsDAOTest() {
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

    @Test
    public void test1() {
        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
        UserInfo userInfo = new UserInfo("levani", "samadashvili", "lsama19@freeuni.edu.ge", "tbilisi", "579204205", "note");
        userInfoDAO.insertUserInfo(userInfo);
        int lastId = SQLPK.getLastPrimaryKey(connection);

        User user = new User(lastId, "skunsy", "hash", false, true, true, 10, 20, 30);
        boolean inserted = sqlUserDAO.insertUser(user);
        assertTrue(inserted);

        inserted = sqlUserDAO.insertUser(user);
        assertFalse(inserted);

        int expectedId = SQLPK.getLastPrimaryKey(connection);

        User gottenUserById = sqlUserDAO.getUser(expectedId);
        User gottenUserByUsername = sqlUserDAO.getUser("skunsy");

        assertEquals(gottenUserById, gottenUserByUsername);

        assertEquals(expectedId, gottenUserById.getId());
        assertEquals(lastId, gottenUserById.getUserInfoId());
        assertEquals("skunsy", gottenUserById.getUsername());
        assertEquals("hash", gottenUserById.getPassword());
        assertFalse(gottenUserById.getIsDealer());
        assertTrue(gottenUserById.getIsAdmin());
        assertTrue(gottenUserById.getIsBanned());
        assertEquals(10, gottenUserById.getNumAuctionsWon());
        assertEquals(20, gottenUserById.getRating(), 0.0);
        assertEquals(30, gottenUserById.getNumReviews());

        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
    }
}
