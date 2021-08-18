package DAO;

import Models.BidderAuction;

import java.util.*;

public interface BidderAuctionDAO {
    /**
     * gets BidderAuction object with id from DB
     * @param id
     * @return BidderAuction object
     */
    public BidderAuction getBidderAuction(int id);

    /**
     * gets a list of BidderAuction objects where bidder his id = user_id;
     * @param user_id
     * @return
     */
    public List<BidderAuction> getBidderAuctionListByUser(int user_id);

    /**
     * removes object BidderAuction with this id from DB
     * @param id
     * @return
     */
    public boolean removeBidderAuction (int id);

    /**
     * removes object BidderAuction with bidder_id = userId & auction_ID = auctionId
     * @param userId
     * @param auctionId
     * @return true if it has been removed, false otherwise
     */
    public boolean removeBidderAuctionByUserAuction (int userId,int auctionId);

    /**
     * inserts a bidderAuction object into the DB
     * @param bidderAuction
     * @return
     */
    public boolean insertBidderAuction(BidderAuction bidderAuction);

}
