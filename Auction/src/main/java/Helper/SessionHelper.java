package Helper;

import Models.User;
import Models.UserInfo;

import javax.servlet.http.HttpSession;

public class SessionHelper implements GeneralConstants {
    /**
     * Checks if there exists a user in session
     * @param session
     * @return true if some user is set in session, false otherwise
     */
    public static boolean checkIfUserExists(HttpSession session) {
        User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);
        UserInfo currentUserInfo = (UserInfo)session.getAttribute(CURRENT_USER_INFO_STRING);

        return currentUser != null && currentUserInfo != null;
    }

    /**
     * Initializes given session, sets default Attribute values
     * @param session
     * @return
     */
    public static void initSession(HttpSession session) {
        session.setAttribute(CURRENT_USER_STRING, null);
        session.setAttribute(CURRENT_USER_INFO_STRING, null);
    }
}
