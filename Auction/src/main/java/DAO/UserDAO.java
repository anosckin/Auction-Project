package DAO;

import Models.User;

import java.util.List;

public interface UserDAO {
    /**
     * Gets User object by its' id
     * @param id
     * @return user with the id if it exists, null otherwise
     */
    public User getUser(int id);

    /**
     * Gets User object by its' username (every username is unique)
     * @param username
     * @return user with the username if it exists, null otherwise
     */
    public User getUser(String username);

    /**
     * Inserts given user object in the store
     * @param user
     * @return true if user was inserted, false otherwise
     */
    public boolean insertUser(User user);

    /**
     * Removes user with the given id
     * @param id
     * @return true if user was removed, false otherwise
     */
    public boolean removeUser(int id);

    /**
     * Removes user with the given username
     * @param username
     * @return true if user was removed, false otherwise
     */
    public boolean removeUser(String username);

    /**
     * Bans user with the given username
     * @param username
     * @return true if user was banned, false otherwise
     */
    public boolean banUser(String username);

    /**
     * makes dealer with the given username
     * @param username
     * @return true if user was made dealer, false otherwise
     */
    public boolean makeDealerUser(String username);

    /**
     * makes admin with the given username
     * @param username
     * @return true if user was made admin, false otherwise
     */
    public boolean makeAdminUser(String username);

    /**
     * Gets every User from store
     * @return list of users, empty list if there are no users
     */
    public List<User> getAllUsers();

    /**
     * Gets top "userCount" users from the store (determined by number
     * of auctions user has won)
     * @param userCount top "userCount" users will be returned
     * @return list of top users
     */
    public List<User> getTopUsers(int userCount);

    /**
     * Deletes every row in Users store
     */
    public void deleteEverything();

    /**
     * updates number of auctions won
     * @param numWon
     * @param id
     */
    void updateAuctionsWon(int numWon, int id);
}
