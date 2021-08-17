package Models;

import Helper.GeneralConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserInfoTest implements GeneralConstants {

    @Test
    public void testBasic1() {
        UserInfo userInfo = new UserInfo(1, "levan", "samadashvili", "@", "qutaisi", "555", "note");

        assertEquals(1, userInfo.getId());
        assertEquals("levan", userInfo.getFirstName());
        assertEquals("samadashvili", userInfo.getLastName());
        assertEquals("@", userInfo.getEmail());
        assertEquals("qutaisi", userInfo.getAddress());
        assertEquals("555", userInfo.getPhoneNumber());
        assertEquals("note", userInfo.getNote());
    }

    @Test
    public void testBasic2() {
        UserInfo userInfo = new UserInfo("levan", "samadashvili", "@", "qutaisi", "555", "note");

        assertEquals(NO_ID, userInfo.getId());
        assertEquals("levan", userInfo.getFirstName());
        assertEquals("samadashvili", userInfo.getLastName());
        assertEquals("@", userInfo.getEmail());
        assertEquals("qutaisi", userInfo.getAddress());
        assertEquals("555", userInfo.getPhoneNumber());
        assertEquals("note", userInfo.getNote());
    }

    @Test
    public void testBasic3() {
        UserInfo userInfo = new UserInfo(1, "levan", "samadashvili", "@", "qutaisi", "555", "note");
        userInfo.setId(3);
        userInfo.setFirstName("nika");
        userInfo.setLastName("salia");
        userInfo.setEmail("nsali19@freeuni.edu.ge");
        userInfo.setAddress("tbilisi");
        userInfo.setPhoneNumber("234234");
        userInfo.setNote("magari");

        assertEquals(3, userInfo.getId());
        assertEquals("nika", userInfo.getFirstName());
        assertEquals("salia", userInfo.getLastName());
        assertEquals("nsali19@freeuni.edu.ge", userInfo.getEmail());
        assertEquals("tbilisi", userInfo.getAddress());
        assertEquals("234234", userInfo.getPhoneNumber());
        assertEquals("magari", userInfo.getNote());
    }

    @Test
    public void testEquals1() {
        UserInfo userInfo1 = new UserInfo(1, "levan", "samadashvili", "@", "qutaisi", "555", "note");
        UserInfo userInfo2 = new UserInfo(1, "levan", "samadashvili", "@", "qutaisi", "555", "note");

        assertEquals(userInfo1, userInfo2);
    }
}
