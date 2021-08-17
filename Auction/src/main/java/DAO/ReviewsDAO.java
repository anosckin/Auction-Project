package DAO;

import Models.Review;

import java.util.List;

public interface ReviewsDAO {
    /**
     * Gets Review object by its' id
     * @param id
     * @return Review with the id if it exists, null otherwise
     */
    public Review getReview(int id);

    /**
     * Inserts given Review object in the store
     * @param review
     * @return true if Review was inserted, false otherwise
     */
    public boolean insertReview(Review review);

    /**
     * Removes Review with the given id
     * @param id
     * @return true if Review was removed, false otherwise
     */
    public boolean removeReview(int id);

    /**
     * Gets every review for dealer
     * @return list of reviews, empty list if there are no messages
     */
    public List<Review> getAllReviewsDealer(int recipient_ID);

    /**
     * Gets every review for dealer
     * @return list of reviews, empty list if there are no messages
     */
    public List<Review> getAllReviewsUser(int reviewer_ID);

    /**
     * Gets every review for dealer
     * @return list of reviews, empty list if there are no messages
     */
    public List<Review> getAllReviews();
}
