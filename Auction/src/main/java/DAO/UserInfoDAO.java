package DAO;

import Models.UserInfo;

import java.util.List;

public interface UserInfoDAO {
    /**
     * Gets a single UserInfo using its' id
     * @param id
     * @return userInfo with the given id, null if it doesn't exist
     */
    public UserInfo getUserInfo(int id);

    /**
     * Inserts given UserInfo object in the store
     * @param userInfo
     * @return true if userInfo was removed, false otherwise
     */
    public boolean insertUserInfo(UserInfo userInfo);

    /**
     * Removes UserInfo with the given id from UserInfo table
     * @param id
     * @return true if removed, false otherwise
     */
    public boolean removeUserInfo(int id);

    /**
     * Gets every UserInfo from the store
     * @return list of UserInfo objects, empty list if there are none
     */
    public List<UserInfo> getAllUserInfos();

    /**
     * Deletes every row in UserInfos store
     */
    public void deleteEverything();
}
