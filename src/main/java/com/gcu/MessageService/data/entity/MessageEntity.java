package com.gcu.MessageService.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class MessageEntity {

    @Id
    private String id;
    private String message;
    private String timestamp;
    private String userID;
    private String conversationID;

    public MessageEntity(){
        id = "";
        message = "";
        timestamp = "";
        userID = "";
        conversationID = "";
    }

    public MessageEntity(String id, String message, String timestamp, String user_id, String conversation_id) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.userID = user_id;
        this.conversationID = conversation_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getConversationID() {
        return conversationID;
    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }
}
