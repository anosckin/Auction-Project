package DAO;

import Models.UserInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUserInfoDAO implements UserInfoDAO {
    private Connection connection;

    public SqlUserInfoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserInfo getUserInfo(int id) {
        UserInfo userInfo = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserInfos" +
                                                                 " WHERE ID=?;");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                userInfo = convertToUserInfo(resultSet);
            }
        } catch (SQLException throwables) { throwables.printStackTrace(); }

        return userInfo;
    }

    @Override
    public List<UserInfo> getAllUserInfos() {
        List<UserInfo> userInfoList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserInfos;");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                userInfoList.add(convertToUserInfo(resultSet));
            }
        } catch (SQLException throwables) { throwables.printStackTrace(); }

        return userInfoList;
    }

    @Override
    public boolean insertUserInfo(UserInfo userInfo) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO UserInfos " +
                    "(first_name, last_name, email, address, phone_number, note)" +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, userInfo.getFirstName());
            stmt.setString(2, userInfo.getLastName());
            stmt.setString(3, userInfo.getEmail());
            stmt.setString(4, userInfo.getAddress());
            stmt.setString(5, userInfo.getPhoneNumber());
            stmt.setString(6, userInfo.getNote());
            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) { return false; }
    }

    @Override
    public boolean removeUserInfo(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM UserInfos" +
                                                                 " WHERE ID=?");
            stmt.setInt(1, id);
            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) { return false; }
    }

    @Override
    public void deleteEverything() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM UserInfos;");
        } catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    /**
     * Converts resultSet into UserInfo object
     * @param resultSet single row of UserInfo table
     * @return corresponding UserInfo object
     */
    private UserInfo convertToUserInfo(ResultSet resultSet) {
        UserInfo userInfo = null;

        try {
            userInfo = new UserInfo(resultSet.getInt(1), resultSet.getString(2),
                                    resultSet.getString(3), resultSet.getString(4),
                                    resultSet.getString(5), resultSet.getString(6),
                                    resultSet.getString(7));
        } catch (SQLException throwables) { throwables.printStackTrace(); }

        return userInfo;
    }
}
