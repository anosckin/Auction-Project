package Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testBasic1() {
        User user = new User(1, 2, "america", "hash", false, true, false, 15, 10, 20);

        assertEquals(1, user.getId());
        assertEquals(2, user.getUserInfoId());
        assertEquals("america", user.getUsername());
        assertEquals("hash", user.getPassword());
        assertFalse(user.getIsDealer());
        assertTrue(user.getIsAdmin());
        assertFalse(user.getIsBanned());
        assertEquals(15, user.getNumAuctionsWon());
        assertEquals(10, user.getRating(), 0.0);
        assertEquals(20, user.getNumReviews());
    }

    @Test
    public void testBasic2() {
        User user = new User(2, "america", "hash", false, true, false, 15, 10, 20);
        assertEquals(User.NO_ID, user.getId());

        user.setId(1);
        user.setUserInfoId(1);
        user.setUsername("me");
        user.setPassword("is");
        user.setIsDealer(true);
        user.setIsAdmin(false);
        user.setIsBanned(true);
        user.setNumReviews(5);
        user.setNumAuctionsWon(1);
        user.setRating(3);

        assertEquals(1, user.getId());
        assertEquals(1, user.getUserInfoId());
        assertEquals("me", user.getUsername());
        assertEquals("is", user.getPassword());
        assertTrue(user.getIsDealer());
        assertFalse(user.getIsAdmin());
        assertTrue(user.getIsBanned());
        assertEquals(5, user.getNumReviews());
        assertEquals(1, user.getNumAuctionsWon());
        assertEquals(3, user.getRating(), 0.0);
    }

    @Test
    public void testEquals1() {
        User user1 = new User(1, 2, "america", "hash", false, true, false, 15, 10, 20);
        User user2 = new User(1, 2, "america", "hash", false, true, false, 15, 10, 20);

        assertEquals(user1, user2);
    }

    @Test
    public void testEquals2() {
        User user1 = new User(5, "username", "password");
        User user2 = new User(5, "username", "password");

        assertEquals(user1, user2);
    }

    @Test
    public void testIncrementNumAuctionsWon1() {
        User user = new User(5, "username", "password");
        assertEquals(0, user.getNumAuctionsWon());

        user.incrementNumAuctionsWon(3);
        assertEquals(3, user.getNumAuctionsWon());

        user.incrementNumAuctionsWon();
        assertEquals(4, user.getNumAuctionsWon());

        user.incrementNumAuctionsWon(10);
        assertEquals(14, user.getNumAuctionsWon());
    }

    @Test
    public void testStatus1() {
        User user = new User(1, "america", "amer");
        assertEquals(User.SILVER, user.getStatus());

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_SILVER);
        assertEquals(User.SILVER, user.getStatus());

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_GOLD);
        assertEquals(User.GOLD, user.getStatus());

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_PLATINUM);
        assertEquals(User.PLATINUM, user.getStatus());
    }

    @Test
    public void testStatus2() {
        User user = new User(1, "america", "amer");
        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_SILVER - 1);
        assertEquals(User.STATUS_UNDEFINED, user.getStatus());
    }

    @Test
    public void testGetNumPossibleConcurrentAuctions1() {
        User user = new User(1, "america", "amer");

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_SILVER - 1);
        assertEquals(User.NUM_POSSIBLE_CONCURRENT_AUCTIONS_UNDEFINED, user.getNumPossibleConcurrentAuctions());

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_SILVER);
        assertEquals(User.NUM_POSSIBLE_CONCURRENT_AUCTIONS_SILVER, user.getNumPossibleConcurrentAuctions());

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_GOLD);
        assertEquals(User.NUM_POSSIBLE_CONCURRENT_AUCTIONS_GOLD, user.getNumPossibleConcurrentAuctions());

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_PLATINUM);
        assertEquals(User.NUM_POSSIBLE_CONCURRENT_AUCTIONS_PLATINUM, user.getNumPossibleConcurrentAuctions());
    }

    @Test
    public void testGetMaxBid1() {
        User user = new User(1, "america", "amer");

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_SILVER - 1);
        assertEquals(User.MAX_BID_UNDEFINED, user.getMaxBid(), 0.0);

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_SILVER);
        assertEquals(User.MAX_BID_SILVER, user.getMaxBid(), 0.0);

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_GOLD);
        assertEquals(User.MAX_BID_GOLD, user.getMaxBid(), 0.0);

        user.setNumAuctionsWon(User.AUCTIONS_NEEDED_FOR_PLATINUM);
        assertEquals(User.MAX_BID_PLATINUM, user.getMaxBid(), 0.0);
    }

    @Test
    public void testAddRating1() {
        User user = new User(1, "america", "amer");
        user.addRating(5);

        assertEquals(5, user.getRating(), 0.0);

        user.addRating(3);
        assertEquals(4, user.getRating(), 0.0);

        user.addRating(3);
        assertEquals(11 / 3.0, user.getRating(), 0.0);

        user.addRating(4);
        assertEquals(15 / 4.0, user.getRating(), 0.0);
    }
}
