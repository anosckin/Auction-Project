package DAO;

import Models.Message;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlMessageDAO implements MessageDAO{
    private Connection connection;

    public SqlMessageDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Message getMessage(int id){
        Message message = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Messages" +
                    " WHERE ID=?;");

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                message = convertToMessage(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return message;
    }

    @Override
    public List<Message> getAllMessagesForUser(int userId) {
        List<Message> messageList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Messages" +
                    " WHERE from_user_ID = ?;");

            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                messageList.add(convertToMessage(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return messageList;
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> messageList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Messages");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                messageList.add(convertToMessage(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return messageList;
    }

    @Override
    public boolean insertMessage(Message message) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Messages" +
                    "(ID, from_user_ID, to_user_ID, message, time_sent)" +
                    "VALUES (?, ?, ?, ?, ?)");

            stmt.setInt(1, message.getId());
            stmt.setInt(2, message.getFromUserId());
            stmt.setInt(3, message.getToUserId());
            stmt.setString(4, message.getMessage());
            stmt.setTimestamp(5, message.getTimeSent());

            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeMessage(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Messages" +
                    " WHERE ID=?;");
            stmt.setInt(1, id);
            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    /**
     * Converts resultSet into Message object
     * @param resultSet a single row from the Message table
     * @return Message object corresponding to the given row
     */
    private Message convertToMessage(ResultSet resultSet) {
        Message result = null;

        try {
            result = new Message(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getString(4),
                    resultSet.getTimestamp(5));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
