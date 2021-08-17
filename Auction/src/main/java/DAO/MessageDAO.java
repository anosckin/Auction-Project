package DAO;

import Models.Message;

import java.util.List;

public interface MessageDAO {
    /**
     * Gets Message object by its' id
     * @param id
     * @return Message with the id if it exists, null otherwise
     */
    public Message getMessage(int id);

    /**
     * Inserts given message object in the store
     * @param message
     * @return true if message was inserted, false otherwise
     */
    public boolean insertMessage(Message message);

    /**
     * Removes Message with the given id
     * @param id
     * @return true if message was removed, false otherwise
     */
    public boolean removeMessage(int id);

    /**
     * Gets every message sent to user
     * @return list of messages, empty list if there are no messages
     */
    public List<Message> getAllMessagesForUser(int userId);

    /**
     * Gets every message
     * @return list of messages, empty list if there are no messages
     */
    public List<Message> getAllMessages();
}
