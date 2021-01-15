package com.gcu.MessageService.model;

import com.gcu.MessageService.data.entity.MessageEntity;

import java.io.Serializable;

public class MessageModel implements Serializable {

    private String ID;
    private String message;
    private String timestamp;
    private String userID;
    private String conversationID;

    public MessageModel(){
        ID = "";
        message = "";
        timestamp = "";
        userID = "";
        conversationID = "";
    }

    public MessageModel(String ID, String message, String timestamp, String userID, String conversationID) {
        this.ID = ID;
        this.message = message;
        this.timestamp = timestamp;
        this.userID = userID;
        this.conversationID = conversationID;
    }

    public MessageModel(MessageEntity message){
        ID = message.getId();
        this.message = message.getMessage();
        timestamp = message.getTimestamp();
        userID = message.getUserID();
        conversationID = message.getConversationID();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public MessageEntity toEntity(){
        return new MessageEntity(ID, message, timestamp, userID, conversationID);
    }
}
