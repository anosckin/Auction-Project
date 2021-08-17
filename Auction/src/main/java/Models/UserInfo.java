package Models;

import Helper.GeneralConstants;

import java.util.Objects;

public class UserInfo implements GeneralConstants {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String note;

    public UserInfo(int id, String firstName, String lastName, String email, String address,
                    String phoneNumber, String note) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.note = note;
    }

    public UserInfo(String firstName, String lastName, String email, String address,
                    String phoneNumber, String note) {
        this(NO_ID, firstName, lastName, email, address, phoneNumber, note);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id == userInfo.getId() && firstName.equals(userInfo.getFirstName())
                && lastName.equals(userInfo.getLastName()) && email.equals(userInfo.getEmail())
                && address.equals(userInfo.getAddress()) && phoneNumber.equals(userInfo.getPhoneNumber()) && note.equals(userInfo.getNote());
    }
}
