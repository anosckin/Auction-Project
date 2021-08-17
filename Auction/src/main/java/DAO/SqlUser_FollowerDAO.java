package DAO;

import Models.Message;
import Models.User_Follower;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlUser_FollowerDAO implements User_FollowerDAO{

    private Connection connection;

    public SqlUser_FollowerDAO (Connection connection){
        this.connection=connection;
    }

    @Override
    public User_Follower getUser_Follower(int id) {
        User_Follower user_follower = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM USer_Followers" +
                    " WHERE ID=?;");

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                user_follower = convertToUser_Follower(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user_follower;
    }

    @Override
    public boolean insertUser_Follower(User_Follower user_follower) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO User_Followers" +
                    "(ID, follower_ID, followee_ID)" +
                    "VALUES (?, ?, ?)");

            stmt.setInt(1, user_follower.getId());
            stmt.setInt(2, user_follower.getFollowerId());
            stmt.setInt(3, user_follower.getFoloweeId());

            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeUser_Follower(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM User_Followers" +
                    " WHERE ID=?;");
            stmt.setInt(1, id);
            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public List<User_Follower> getAllUser_Follower() {
        List<User_Follower> User_FollowerList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User_Followers");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                User_FollowerList.add(convertToUser_Follower(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return User_FollowerList;
    }


    /**
     * Converts resultSet into User_Follower object
     * @param resultSet a single row from the User_follower table
     * @return User_Follower object corresponding to the given row
     */
    private User_Follower convertToUser_Follower(ResultSet resultSet) {
        User_Follower result = null;

        try {
            result = new User_Follower(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getInt(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
