package Services;

import DAO.UserDAO;
import DAO.UserInfoDAO;
import Helper.SQLPK;
import Models.User;
import Models.UserInfo;

import java.sql.Connection;

/** This class is responsible for managing users in the store */
public class UserService {
    private UserDAO userDAO;
    private UserInfoDAO userInfoDAO;
    private Connection connection;

    public UserService(UserDAO userDAO, UserInfoDAO userInfoDAO, Connection connection) {
        this.userDAO = userDAO;
        this.userInfoDAO = userInfoDAO;
        this.connection = connection;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts UserInfo object in UserInfo table and corresponding User object in User table
     * @param username username
     * @param passwordHash password hash
     * @param userInfo UserInfo object (probably with NO_ID)
     * @return true if user was inserted, false otherwise
     */
    public boolean insertUser(String username, String passwordHash, UserInfo userInfo) {
        boolean userInfoInserted = userInfoDAO.insertUserInfo(userInfo);
        int lastId = SQLPK.getLastPrimaryKey(connection);

        if (!userInfoInserted) {
            return false;
        }

        User newUser = new User(lastId, username, passwordHash);
        boolean userInserted = userDAO.insertUser(newUser);

        if (!userInserted) {
            userInfoDAO.removeUserInfo(lastId);
            return false;
        }

        return true;
    }
}
