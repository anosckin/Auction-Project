package Helper;

import Helper.GeneralConstants;

import java.sql.*;

public class SQLPK implements GeneralConstants {

    /**
     * Returns newly inserted primary key
     * @return
     */
    public static int getLastPrimaryKey(Connection connection) {
        int result = NO_ID;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT LAST_INSERT_ID();");
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                result = Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
