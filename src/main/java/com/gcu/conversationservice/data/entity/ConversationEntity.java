package com.gcu.conversationservice.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "conversations")
public class ConversationEntity {

    @Id
    private String id;
    private String user_1;
    private String user_2;

    public ConversationEntity(){
        id = "";
        user_1 = "";
        user_2 = "";
    }

    public ConversationEntity(String id, String user_1, String user_2) {
        this.id = id;
        this.user_1 = user_1;
        this.user_2 = user_2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_1() {
        return user_1;
    }

    public void setUser_1(String user_1) {
        this.user_1 = user_1;
    }

    public String getUser_2() {
        return user_2;
    }

    public void setUser_2(String user_2) {
        this.user_2 = user_2;
    }
}
