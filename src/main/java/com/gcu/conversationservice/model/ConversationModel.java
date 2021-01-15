package com.gcu.conversationservice.model;

import com.gcu.conversationservice.data.entity.ConversationEntity;

import java.io.Serializable;

public class ConversationModel implements Serializable {

    private String ID;
    private String user1;
    private String user2;

    public ConversationModel(){
        ID = "";
        user1 = "";
        user2 = "";
    }

    public ConversationModel(String ID, String user1, String user2) {
        this.ID = ID;
        this.user1 = user1;
        this.user2 = user2;
    }

    public ConversationModel(ConversationEntity conversation){
        ID = conversation.getId();
        user1 = conversation.getUser_1();
        user2 = conversation.getUser_2();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public ConversationEntity toEntity(){
        return new ConversationEntity(ID, user1, user2);
    }
}
