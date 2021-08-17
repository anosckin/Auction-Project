package Helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    /**
     * Given a byte[] array, produces a hex String,
     * such as "234a6f". with 2 chars for each byte in the array.
     * @param bytes bytes to convert to string
     * @return printableHash
     */
    private static String hexToString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) builder.append('0'); // leading 0
            builder.append(Integer.toString(val, 16));
        }

        return builder.toString();
    }

    /**
     * Hashes password string using SHA-256 algorithm
     * @return hash string of password
     */
    public static String hash(String password) {
        MessageDigest md = null;
        byte[] byteArray = password.getBytes(StandardCharsets.UTF_8);

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(byteArray);
            byte[] hashBytes = md.digest();

            String printableHash = hexToString(hashBytes);
            return printableHash;
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return "";
    }
}
