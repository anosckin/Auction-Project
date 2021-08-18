package Models;

import Helper.GeneralConstants;

public class BidderAuction implements GeneralConstants {
    private int id;
    private int bidderId;
    private int auctionId;
    private int bid;

    public BidderAuction (int id,int bidderId,int auctionId,int bid){
        this.id=id;
        this.bidderId=bidderId;
        this.auctionId=auctionId;
        this.bid=bid;
    }

    public BidderAuction (int bidderId,int auctionId,int bid){
        this (NO_ID,bidderId,auctionId,bid);
    }

    public int getId(){
        return id;
    }

    public int getBidderId(){
        return bidderId;
    }

    public int getAuctionId(){
        return auctionId;
    }

    public int getBid(){
        return bid;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setBidderId(int bidderId){
        this.bidderId=bidderId;
    }

    public void setAuctionId (int auctionId){
        this.auctionId=auctionId;
    }

    public void setBid (int bid){
        this.bid=bid;
    }

    public boolean equals (BidderAuction o){
        return (o.getId()==this.id);
    }
}
