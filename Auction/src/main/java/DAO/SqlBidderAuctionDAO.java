package DAO;

import Models.BidderAuction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlBidderAuctionDAO implements BidderAuctionDAO{
    public static final String ATTRIBUTE_NAME = "SQL_BID_AUC_DAO";
    private Connection connection;

    public SqlBidderAuctionDAO (Connection connection){
        this.connection=connection;
    }

    @Override
    public BidderAuction getBidderAuction(int id) {
        BidderAuction result = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM bidder_auctions WHERE ID = ?");
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                result = convertToBidderAuction(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public List<BidderAuction> getBidderAuctionListByUser(int user_id) {
        List <BidderAuction> userBids = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM bidder_auctions WHERE bidder_ID = ?");
            stmt.setInt(1,user_id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                userBids.add(convertToBidderAuction(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userBids;
    }

    @Override
    public boolean removeBidderAuction(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM bidder_auctions WHERE ID=?;");
            stmt.setInt(1, id);
            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeBidderAuctionByUserAuction(int userId, int auctionId) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM bidder_auctions "
                        + "WHERE bidder_ID = ? AND auction_ID = ?; ");
            stmt.setInt(1, userId);
            stmt.setInt(2, auctionId);
            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean insertBidderAuction(BidderAuction bidderAuction) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO bidder_auctions" +
                    "(bidder_ID, auction_ID, bid)" +
                    "VALUES (?, ?, ?)");

            stmt.setInt(1, bidderAuction.getBidderId());
            stmt.setInt(2, bidderAuction.getAuctionId());
            stmt.setInt(3, bidderAuction.getBid());

            int numRowsAffected = stmt.executeUpdate();

            return (numRowsAffected == 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }


    /**
     * converts resultSet  into BidderAuction object
     * @param resultSet
     * @return BidderAuction object
     */
    private BidderAuction convertToBidderAuction (ResultSet resultSet){
        BidderAuction result = null;
        try {
            result = new BidderAuction(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getInt(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


}
