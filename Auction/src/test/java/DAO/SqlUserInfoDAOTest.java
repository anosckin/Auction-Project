package DAO;

import Helper.SQLPK;
import Models.UserInfo;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/* TABLES SHOULD BE EMPTY WHEN RUNNING THESE TESTS
 *  Or tests will empty them by themselves */
public class SqlUserInfoDAOTest {
    private UserInfoDAO userInfoDAO;
    private Connection connection;

    public SqlUserInfoDAOTest() {
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

            UserDAO sqlUserDAO = new SqlUserDAO(connection);
            userInfoDAO = new SqlUserInfoDAO(connection);

            sqlUserDAO.deleteEverything();
            userInfoDAO.deleteEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        userInfoDAO.deleteEverything();

        UserInfo userInfo = new UserInfo("Levani", "Samadashvili", "lsama19@freeuni.edu.ge", "tbilisi", "mmm", "noTe");
        boolean inserted = userInfoDAO.insertUserInfo(userInfo);
        assertTrue(inserted);

        int lastId = SQLPK.getLastPrimaryKey(connection);
        userInfo.setId(lastId);

        inserted = userInfoDAO.insertUserInfo(userInfo);
        assertTrue(inserted);

        UserInfo gottenUserInfo = userInfoDAO.getUserInfo(lastId);
        assertEquals(userInfo, gottenUserInfo);

        userInfoDAO.deleteEverything();
    }

    @Test
    public void test2() {
        userInfoDAO.deleteEverything();

        UserInfo userInfo = new UserInfo("Levani", "Samadashvili", "lsama19@freeuni.edu.ge", "tbilisi", "mmm", "noTe");
        boolean inserted = userInfoDAO.insertUserInfo(userInfo);
        assertTrue(inserted);

        int lastId = SQLPK.getLastPrimaryKey(connection);
        userInfo.setId(lastId);

        UserInfo gottenUserInfo = userInfoDAO.getUserInfo(lastId + 1);
        assertNull(gottenUserInfo);

        boolean removed = userInfoDAO.removeUserInfo(lastId + 1);
        assertFalse(removed);

        removed = userInfoDAO.removeUserInfo(lastId);
        assertTrue(removed);

        userInfoDAO.deleteEverything();
    }

    @Test
    public void test3() {
        userInfoDAO.deleteEverything();

        UserInfo userInfo1 = new UserInfo("Levani", "Samadashvili", "lsama19@freeuni.edu.ge", "tbilisi", "mmm", "noTe");
        userInfoDAO.insertUserInfo(userInfo1);

        int lastId = SQLPK.getLastPrimaryKey(connection);
        userInfo1.setId(lastId);

        UserInfo userInfo2 = new UserInfo("Levan", "Samadashvil", "sama19@freeuni.edu.ge", "tbilisi", "mmm", "noTe");
        userInfoDAO.insertUserInfo(userInfo2);

        lastId = SQLPK.getLastPrimaryKey(connection);
        userInfo2.setId(lastId);

        UserInfo userInfo3 = new UserInfo("Levani", "Samadashvli", "lsama19freeuni.edu.ge", "tbilisi", "mmm", "noTe");
        userInfoDAO.insertUserInfo(userInfo3);

        lastId = SQLPK.getLastPrimaryKey(connection);
        userInfo3.setId(lastId);

        List<UserInfo> userInfoList = userInfoDAO.getAllUserInfos();
        assertEquals(3, userInfoList.size());

        assertTrue(userInfoList.contains(userInfo1));
        assertTrue(userInfoList.contains(userInfo2));
        assertTrue(userInfoList.contains(userInfo3));

        userInfoDAO.deleteEverything();
    }
}
