package Models;


import Helper.GeneralConstants;

public class User_Follower implements GeneralConstants {

    private int id;
    private int followerId;
    private int followeeId;

    public User_Follower(int id , int followerId , int followeeId) {
        this.id = id;
        this.followerId=followerId;
        this.followeeId=followeeId;
    }

    public User_Follower(int followerId , int followeeId) {
        this (NO_ID,followerId,followeeId);
    }

    public int getId() {
        return id;
    }

    public int getFollowerId() {
        return followerId;
    }

    public int getFoloweeId() {
        return followeeId;
    }

    public void setId(int id) {
        this.id=id;
    }

    public void setFollowerId(int followerId) {
        this.followerId=followerId;
    }

    public void setFoloweeId(int followeeId) {
        this.followeeId=followeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User_Follower follow = (User_Follower) o;

        return (id == follow.getId()
                && followerId == follow.getFollowerId()
                && followeeId == follow.getFoloweeId());
    }
}
