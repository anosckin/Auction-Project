package DAO;

import Models.Review;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlReviewsDao implements ReviewsDAO{
    public static final String ATTRIBUTE_NAME = "SQL_REVIEWS_DAO";
    private Connection connection;

    public SqlReviewsDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Review getReview(int id){
        Review review = null;
        
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Reviews" +
                    " WHERE ID=?;");

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                review = convertToReview(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return review;
    }

    @Override
    public boolean insertReview(Review review){
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Reviews" +
                    "(reviewer_ID, recipient_ID, score, review)" +
                    "VALUES (?, ?, ?, ?)");

            stmt.setInt(1, review.getReviewerId());
            stmt.setInt(2, review.getRecipientId());
            stmt.setInt(3, review.getScore());
            stmt.setString(4, review.getReview());

            int numRowsAffected = stmt.executeUpdate();
            SqlUserDAO userDao = new SqlUserDAO(connection);
            boolean ans = userDao.updateRecipientRating(review.getScore(), review.getRecipientId());

            return (numRowsAffected == 1 && ans);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeReview(int id){
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Reviews" +
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
    public List<Review> getAllReviewsDealer(int recipient_ID){
        List<Review> reviewList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Reviews" +
                    " WHERE recipient_ID = ?;");

            stmt.setInt(1, recipient_ID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                reviewList.add(convertToReview(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reviewList;
    }

    @Override
    public List<Review> getAllReviewsUser(int reviewer_ID){
        List<Review> reviewList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Reviews" +
                    " WHERE reviewer_ID = ?;");

            stmt.setInt(1, reviewer_ID);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                reviewList.add(convertToReview(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reviewList;
    }

    @Override
    public List<Review> getAllReviews(){
        List<Review> reviewList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Reviews");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                reviewList.add(convertToReview(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reviewList;
    }

    /**
     * Converts resultSet into Review object
     * @param resultSet a single row from the Reviews table
     * @return Review object corresponding to the given row
     */
    private Review convertToReview(ResultSet resultSet) {
        Review result = null;

        try {
            result = new Review(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getInt(4),
                    resultSet.getString(5));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
