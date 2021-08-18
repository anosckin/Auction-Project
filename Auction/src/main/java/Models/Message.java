package Models;

import Helper.GeneralConstants;

import java.sql.Timestamp;

public class Message implements GeneralConstants{

    private int id;
    private int fromUserId;
    private int toUserId;
    private String message;
    private Timestamp timeSent;

    public Message(int id , int fromUserId , int toUserId , String message,Timestamp time_sent) {
        this.id = id;
        this.fromUserId=fromUserId;
        this.toUserId=toUserId;
        this.message=message;
        this.timeSent=time_sent;
    }

    public Message(int fromUserId,int toUserId,String message, Timestamp time_sent) {
        this(NO_ID,fromUserId,toUserId,message,time_sent);
    }

    public int getId() {
        return id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimeSent() {
        return timeSent;
    }

    public void setId(int id) {
        this.id=id;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId=fromUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId=toUserId;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    public void setTimeSent(Timestamp timeSent) {
        this.timeSent=timeSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message currentMessage = (Message) o;

        return (id == currentMessage.getId()
                && toUserId == currentMessage.getToUserId()
                && fromUserId == currentMessage.getFromUserId()
                && message.equals(currentMessage.getMessage())
                && timeSent.equals(currentMessage.getTimeSent()));
    }
}
