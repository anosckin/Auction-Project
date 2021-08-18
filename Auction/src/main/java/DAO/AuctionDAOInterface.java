package DAO;

import Models.Auction;
import Models.BidderAuction;
import Models.User;

import java.util.List;

public interface AuctionDAOInterface {

    /**
     * return auction with given id.
     * @param id
     * @return
     */
    public Auction getAuction(int id);

    /**
     * Inserts given auction object in the store
     * @param auction
     * @return true if user was inserted, false otherwise
     */
    public boolean insertAuction(Auction auction);

    /**
     * Removes auction with the given id
     * @param id
     * @return true if auction was removed, false otherwise
     */
    public boolean removeAuction(int id);

    /**
     * Gets every Auctions from store
     * @return list of auctions, empty list if there are no auctions
     */
    public List<Auction> getAllAuctions();

    /**
     * returns a list of auctions that the user_id has won
     * @param user_id
     * @return
     */
    public List<Auction> getWonAuctions (int user_id);
}
