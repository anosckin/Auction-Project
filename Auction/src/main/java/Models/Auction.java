package Models;

import Helper.GeneralConstants;

import java.util.Date;

public class Auction implements GeneralConstants {
    private int id;
    private int seller_id;
    private int current_bidder_id;
    private int starting_price;
    private int min_increment;
    private int current_price;
    private Date end_date;
    private String item_name;
    private String item_desciption;

    public Auction(int id, int seller_id, int current_bidder_id, int starting_price, int min_increment, int current_price, Date end_date,
                   String item_name, String item_desciption) {
        this.id = id;
        this.seller_id = seller_id;
        this.current_bidder_id = current_bidder_id;
        this.starting_price = starting_price;
        this.min_increment = min_increment;
        this.current_price = current_price;
        this.end_date = end_date;
        this.item_name = item_name;
        this.item_desciption = item_desciption;
    }


    public int getId() {
        return id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public int getCurrent_bidder_id() {
        return current_bidder_id;
    }

    public int getStarting_price() {
        return starting_price;
    }

    public int getMin_increment() {
        return min_increment;
    }

    public int getCurrent_price() {
        return current_price;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_description() {
        return item_desciption;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public void setCurrent_bidder_id(int current_bidder_id) {
        this.current_bidder_id = current_bidder_id;
    }

    public void setStarting_price(int starting_price) {
        this.starting_price = starting_price;
    }

    public void setMin_increment(int min_increment) {
        this.min_increment = min_increment;
    }

    public void setCurrent_price(int current_price) {
        this.current_price = current_price;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_desciption(String item_desciption) {
        this.item_desciption = item_desciption;
    }

    public boolean isActive() {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        if (this.getEnd_date().compareTo(date) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;

        return id == auction.getId();
    }
}
