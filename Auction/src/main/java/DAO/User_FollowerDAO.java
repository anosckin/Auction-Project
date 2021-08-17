package DAO;



import Models.User_Follower;

import java.util.List;

public interface User_FollowerDAO{
        /**
         * Gets User_Follower object by its' id
         * @param id
         * @return User_Follower with the id if it exists, null otherwise
         */
        public User_Follower getUser_Follower(int id);

        /**
         * Inserts given User_Follower object in the store
         * @param user_follower
         * @return true if User_Follower was inserted, false otherwise
         */
        public boolean insertUser_Follower(User_Follower user_follower);

        /**
         * Removes User_Follower with the given id
         * @param id
         * @return true if User_Follower was removed, false otherwise
         */
        public boolean removeUser_Follower(int id);

        /**
         * Gets every User_Follower
         * @return list of User_Followers, empty list if there are no User_Followers
         */
        public List<User_Follower> getAllUser_Follower();
}
