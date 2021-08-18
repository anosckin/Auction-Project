package Models;

import Helper.GeneralConstants;

import java.util.Objects;

public class User implements GeneralConstants {
    /* Constants for User status */
    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;
    public static final int STATUS_UNDEFINED = -1;

    /* Constants to determine user's status
     *  if you change these, some tests won't work */
    public static final int AUCTIONS_NEEDED_FOR_SILVER = 0;
    public static final int AUCTIONS_NEEDED_FOR_GOLD = 10;
    public static final int AUCTIONS_NEEDED_FOR_PLATINUM = 50;

    /* Constants to determine maximum number of possible
     * auctions user can participate in
     * #### THESE NUMBERS ARE SUBJECT TO CHANGE ####
     * PUBLIC FOR TESTING*/
    public static final int NUM_POSSIBLE_CONCURRENT_AUCTIONS_UNDEFINED = 0;
    public static final int NUM_POSSIBLE_CONCURRENT_AUCTIONS_SILVER = 3;
    public static final int NUM_POSSIBLE_CONCURRENT_AUCTIONS_GOLD = 10;
    public static final int NUM_POSSIBLE_CONCURRENT_AUCTIONS_PLATINUM = 50;

    public static final double MAX_BID_UNDEFINED = 0.0;
    public static final double MAX_BID_SILVER = 1000.0;
    public static final double MAX_BID_GOLD = 10000.0;
    public static final double MAX_BID_PLATINUM = 100000.0;

    private int id;
    private int userInfoId;
    private String username;
    private String password; // SHA-256 Hash string of user's raw password
    private boolean isDealer;
    private boolean isAdmin;
    private boolean isBanned;
    private int numAuctionsWon;
    private double rating;
    private int numReviews;
    private int status; // UNDEFINED, SILVER, GOLD, PLATINUM
    private int sumReviewScores;

    public User(int id, int userInfoId, String username, String password,
                boolean isDealer, boolean isAdmin, boolean isBanned, int numAuctionsWon,
                double rating, int numReviews) {
        this.id = id;
        this.userInfoId = userInfoId;
        this.username = username;
        this.password = password;
        this.isDealer = isDealer;
        this.isAdmin = isAdmin;
        this.isBanned = isBanned;
        this.numAuctionsWon = numAuctionsWon;
        this.rating = rating;
        this.numReviews = numReviews;
        this.status = calculateStatus(numAuctionsWon);
        this.sumReviewScores = 0; // might change later
    }

    public User(int userInfoId, String username, String password,
                boolean isDealer, boolean isAdmin, boolean isBanned, int numAuctionsWon,
                int rating, int numReviews) {
        this(NO_ID, userInfoId, username, password, isDealer, isAdmin, isBanned, numAuctionsWon, rating, numReviews);
    }

    public User(int userInfoId, String username, String password) {
        this(NO_ID, userInfoId, username, password, false, false, false, 0, 0, 0);
    }

    public int getId() {
        return id;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsDealer() {
        return isDealer;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public boolean getIsBanned() {
        return isBanned;
    }

    public int getNumAuctionsWon() {
        return numAuctionsWon;
    }

    public double getRating() {
        return rating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password Hash value of users raw password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    public void setNumAuctionsWon(int numAuctionsWon) {
        this.numAuctionsWon = numAuctionsWon;
        this.status = calculateStatus(numAuctionsWon);
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    /**
     * Increments numAuctionsWon by given value "increment"
     * @param increment value to add to number of won auctions
     */
    public void incrementNumAuctionsWon(int increment) {
        this.numAuctionsWon += increment;
        this.status = calculateStatus(this.numAuctionsWon);
    }

    /**
     * Increments numAuctionsWon by 1
     */
    public void incrementNumAuctionsWon() {
        incrementNumAuctionsWon(1);
    }

    /**
     * Gets a single review score and updates rating accordingly
     * @param reviewScore score of the new review
     */
    public void addRating(int reviewScore) {
        numReviews++;
        sumReviewScores += reviewScore;

        setRating((double)sumReviewScores / numReviews);
    }

    /**
     * Calculates maximum number of possible auctions user can participate in
     * @return maximum number of possible auctions user can participate in
     */
    public int getNumPossibleConcurrentAuctions() {
        int status = getStatus();

        switch (status) {
            case SILVER:
                return NUM_POSSIBLE_CONCURRENT_AUCTIONS_SILVER;
            case GOLD:
                return NUM_POSSIBLE_CONCURRENT_AUCTIONS_GOLD;
            case PLATINUM:
                return NUM_POSSIBLE_CONCURRENT_AUCTIONS_PLATINUM;
            default:
                return NUM_POSSIBLE_CONCURRENT_AUCTIONS_UNDEFINED;
        }
    }

    /**
     * Calculates maximum bid this user can place on an item (using status)
     * @return maximum possible bid
     */
    public double getMaxBid() {
        int status = getStatus();

        switch (status) {
            case SILVER:
                return MAX_BID_SILVER;
            case GOLD:
                return MAX_BID_GOLD;
            case PLATINUM:
                return MAX_BID_PLATINUM;
            default:
                return MAX_BID_UNDEFINED;
        }
    }

    /**
     * Calculates user's status
     * @param numAuctionsWon number of auctions won by user
     */
    private int calculateStatus(int numAuctionsWon) {
        if (numAuctionsWon >= AUCTIONS_NEEDED_FOR_PLATINUM) {
            return PLATINUM;
        } else if (numAuctionsWon >= AUCTIONS_NEEDED_FOR_GOLD) {
            return GOLD;
        } else if (numAuctionsWon >= AUCTIONS_NEEDED_FOR_SILVER) {
            return SILVER;
        }

        return STATUS_UNDEFINED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;

        return id == user.getId() && userInfoId == user.getUserInfoId() && isDealer == user.getIsDealer()
                && isAdmin == user.getIsAdmin() && isBanned == user.getIsBanned()
                && numAuctionsWon == user.getNumAuctionsWon() && rating == user.getRating()
                && numReviews == user.getNumReviews()
                && username.equals(user.getUsername()) && password.equals(user.getPassword());
    }
}
