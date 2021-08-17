package DAO;

import Helper.SQLPK;
import Models.User;
import Models.UserInfo;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/* TABLES SHOULD BE EMPTY WHEN RUNNING THESE TESTS
*  Or tests will empty them by themselves */
public class SqlUserDAOTest {
    private SqlUserDAO sqlUserDAO;
    private UserInfoDAO userInfoDAO;
    private Connection connection;

    public SqlUserDAOTest() {
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

    @Test
    public void test2() {
        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
        UserInfo userInfo = new UserInfo("LeVANI", "sAMaDaShvilI", "lsaMA19@fREeuni.edu.ge", "tbilISI", "5a04205", "NOTENOTE");
        userInfoDAO.insertUserInfo(userInfo);
        int lastId = SQLPK.getLastPrimaryKey(connection);

        User user = new User(lastId, "SKunSY", "haSH235", false, true, true, 10, 20, 30);
        boolean inserted = sqlUserDAO.insertUser(user);
        assertTrue(inserted);

        inserted = sqlUserDAO.insertUser(user);
        assertFalse(inserted);

        int expectedId = SQLPK.getLastPrimaryKey(connection);

        User gottenUserById = sqlUserDAO.getUser(expectedId);
        User gottenUserByUsername = sqlUserDAO.getUser("SKunSY");
        User invalidUser = sqlUserDAO.getUser("skunsy");
        User invalidUser2 = sqlUserDAO.getUser("Skunsy");
        User invalidUser3 = sqlUserDAO.getUser(-1);

        assertNull(invalidUser);
        assertNull(invalidUser2);
        assertNull(invalidUser3);

        assertEquals(gottenUserById, gottenUserByUsername);

        assertEquals(expectedId, gottenUserById.getId());
        assertEquals(lastId, gottenUserById.getUserInfoId());
        assertEquals("SKunSY", gottenUserById.getUsername());
        assertEquals("haSH235", gottenUserById.getPassword());
        assertFalse(gottenUserById.getIsDealer());
        assertTrue(gottenUserById.getIsAdmin());
        assertTrue(gottenUserById.getIsBanned());
        assertEquals(10, gottenUserById.getNumAuctionsWon());
        assertEquals(20, gottenUserById.getRating(), 0.0);
        assertEquals(30, gottenUserById.getNumReviews());

        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
    }

    @Test
    public void test3() {
        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();

        UserInfo userInfo = new UserInfo("levani", "samadashvili", "lsama19@freeuni.edu.ge", "tbilisi", "579204205", "note");
        userInfoDAO.insertUserInfo(userInfo);
        int lastId = SQLPK.getLastPrimaryKey(connection);

        User user1 = new User(lastId, "skunsy", "hash", false, true, true, 10, 20, 30);
        boolean inserted = sqlUserDAO.insertUser(user1);
        assertTrue(inserted);

        int lastUserId = SQLPK.getLastPrimaryKey(connection);
        user1.setId(lastUserId);

        User user2 = new User(lastId, "NIka", "MErabi");
        inserted = sqlUserDAO.insertUser(user2);
        assertTrue(inserted);

        lastUserId = SQLPK.getLastPrimaryKey(connection);
        user2.setId(lastUserId);

        User user3 = new User(lastId, "StillResisting", "chAnge");
        inserted = sqlUserDAO.insertUser(user3);
        assertTrue(inserted);

        lastUserId = SQLPK.getLastPrimaryKey(connection);
        user3.setId(lastUserId);

        List<User> userList = sqlUserDAO.getAllUsers();
        assertEquals(3, userList.size());
        assertTrue(userList.contains(user1));
        assertTrue(userList.contains(user2));
        assertTrue(userList.contains(user3));

        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
    }

    @Test
    public void test4() {
        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();

        UserInfo userInfo = new UserInfo("LeVANI", "sAMaDaShvilI", "lsaMA19@fREeuni.edu.ge", "tbilISI", "5a04205", "NOTENOTE");
        userInfoDAO.insertUserInfo(userInfo);
        int lastId = SQLPK.getLastPrimaryKey(connection);

        User user = new User(lastId, "SKunSY", "haSH235", false, true, true, 10, 20, 30);
        boolean inserted = sqlUserDAO.insertUser(user);
        assertTrue(inserted);

        int insertedUserId = SQLPK.getLastPrimaryKey(connection);

        boolean removed = sqlUserDAO.removeUser("skunsy");
        assertFalse(removed);

        removed = sqlUserDAO.removeUser(insertedUserId + 1);
        assertFalse(removed);

        removed = sqlUserDAO.removeUser("SKunSY");
        assertTrue(removed);

        inserted = sqlUserDAO.insertUser(user);
        assertTrue(inserted);

        insertedUserId = SQLPK.getLastPrimaryKey(connection);

        removed = sqlUserDAO.removeUser(insertedUserId);
        assertTrue(removed);

        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
    }

    @Test
    public void test5() {
        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();

        List<User> topUsers = sqlUserDAO.getTopUsers(0);
        assertTrue(topUsers.isEmpty());

        UserInfo userInfo1 = new UserInfo("a", "a", "a", "a", "a", "a");
        UserInfo userInfo2 = new UserInfo("b", "b", "b", "b", "b", "b");
        UserInfo userInfo3 = new UserInfo("c", "c", "c", "c", "c", "c");
        UserInfo userInfo4 = new UserInfo("d", "d", "d", "d", "d", "d");
        UserInfo userInfo5 = new UserInfo("e", "e", "e", "e", "e", "e");
        UserInfo userInfo6 = new UserInfo("f", "f", "f", "f", "f", "f");
        UserInfo userInfo7 = new UserInfo("g", "g", "g", "g", "g", "g");
        UserInfo userInfo8 = new UserInfo("h", "h", "h", "h", "h", "h");
        UserInfo userInfo9 = new UserInfo("i", "i", "i", "i", "i", "i");
        UserInfo userInfo10 = new UserInfo("j", "j", "j", "j", "j", "j");
        UserInfo userInfo11 = new UserInfo("k", "k", "k", "k", "k", "k");

        userInfoDAO.insertUserInfo(userInfo1);
        int lastId = SQLPK.getLastPrimaryKey(connection);
        User user1 = new User(lastId, "a", "a", false, false, false, 20, 0, 0);

        userInfoDAO.insertUserInfo(userInfo2);
        lastId = SQLPK.getLastPrimaryKey(connection);
        User user2 = new User(lastId, "b", "b", false, false, false, 19, 0, 0);

        userInfoDAO.insertUserInfo(userInfo3);
        lastId = SQLPK.getLastPrimaryKey(connection);
        User user3 = new User(lastId, "c", "c", false, false, false, 17, 0, 0);

        userInfoDAO.insertUserInfo(userInfo4);
        lastId = SQLPK.getLastPrimaryKey(connection);
        User user4 = new User(lastId, "d", "d", false, false, false, 16, 0, 0);

        userInfoDAO.insertUserInfo(userInfo5);
        lastId = SQLPK.getLastPrimaryKey(connection);
        User user5 = new User(lastId, "e", "e", false, false, false, 15, 0, 0);

        userInfoDAO.insertUserInfo(userInfo6);
        lastId = SQLPK.getLastPrimaryKey(connection);
        User user6 = new User(lastId, "f", "f", false, false, false, 14, 0, 0);

        userInfoDAO.insertUserInfo(userInfo7);
        lastId = SQLPK.getLastPrimaryKey(connection);
        User user7 = new User(lastId, "g", "g", false, false, false, 13, 0, 0);

        userInfoDAO.insertUserInfo(userInfo8);
        lastId = SQLPK.getLastPrimaryKey(connection);
        User user8 = new User(lastId, "h", "h", false, false, false, 10, 0, 0);

        userInfoDAO.insertUserInfo(userInfo9);
        lastId = SQLPK.getLastPrimaryKey(connection);
        User user9 = new User(lastId, "i", "i", false, false, false, 9, 0, 0);

        userInfoDAO.insertUserInfo(userInfo10);
        lastId = SQLPK.getLastPrimaryKey(connection);
        User user10 = new User(lastId, "j", "j", false, false, false, 3, 0, 0);

        userInfoDAO.insertUserInfo(userInfo11);
        lastId = SQLPK.getLastPrimaryKey(connection);
        User user11 = new User(lastId, "k", "k", false, false, false, 1, 0, 0);


        sqlUserDAO.insertUser(user1);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user1.setId(lastId);

        sqlUserDAO.insertUser(user2);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user2.setId(lastId);

        sqlUserDAO.insertUser(user3);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user3.setId(lastId);

        sqlUserDAO.insertUser(user4);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user4.setId(lastId);

        sqlUserDAO.insertUser(user5);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user5.setId(lastId);

        sqlUserDAO.insertUser(user6);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user6.setId(lastId);

        sqlUserDAO.insertUser(user7);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user7.setId(lastId);

        sqlUserDAO.insertUser(user8);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user8.setId(lastId);

        sqlUserDAO.insertUser(user9);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user9.setId(lastId);

        sqlUserDAO.insertUser(user10);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user10.setId(lastId);

        sqlUserDAO.insertUser(user11);
        lastId = SQLPK.getLastPrimaryKey(connection);
        user11.setId(lastId);

        List<User> top5 = sqlUserDAO.getTopUsers(5);
        assertEquals(top5.get(0), user1);
        assertEquals(top5.get(1), user2);
        assertEquals(top5.get(2), user3);
        assertEquals(top5.get(3), user4);
        assertEquals(top5.get(4), user5);

        List<User> top10 = sqlUserDAO.getTopUsers(10);
        assertEquals(top10.get(0), user1);
        assertEquals(top10.get(1), user2);
        assertEquals(top10.get(2), user3);
        assertEquals(top10.get(3), user4);
        assertEquals(top10.get(4), user5);
        assertEquals(top10.get(5), user6);
        assertEquals(top10.get(6), user7);
        assertEquals(top10.get(7), user8);
        assertEquals(top10.get(8), user9);
        assertEquals(top10.get(9), user10);

        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
    }
}
