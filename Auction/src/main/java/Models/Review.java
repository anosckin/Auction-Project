package Models;

import Helper.GeneralConstants;

public class Review implements GeneralConstants {

    private int id;
    private int itemId;
    private int reviewerId;
    private int recipientId;
    private int score;
    private String review;

    public Review(int id, int itemId, int reviewerId, int recipientId, int score, String review) {
        this.id = id;
        this.itemId = itemId;
        this.reviewerId = reviewerId;
        this.recipientId = recipientId;
        this.score = score;
        this.review = review;
    }

    public Review(int itemId, int reviewerId, int recipientId, int score, String review) {
        this(NO_ID, itemId, reviewerId, recipientId, score, review);
    }

    public int getId() {
        return id;
    }

    public int getItemId() {
        return itemId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public int getScore() {
        return score;
    }

    public String getReview() {
        return review;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    /**
     * @param score must be between 1 and 5, anything else is turned to -1
     */
    public void setScore(int score) {
        if (1 <= score && score <= 5) {
            this.score = score;
        } else {
            this.score = -1;
        }
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review currentReview = (Review) o;

        return (id == currentReview.getId()
                && reviewerId == currentReview.getReviewerId()
                && recipientId == currentReview.getRecipientId()
                && score == currentReview.getScore()
                && review.equals(currentReview.getReview()));
    }
}
