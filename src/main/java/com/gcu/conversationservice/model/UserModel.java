package com.gcu.conversationservice.model;

public class UserModel {

    private String ID;
    private String email;
    private CredentialsModel credentials;

    public UserModel(){
        ID = "";
        email = "";
        credentials = new CredentialsModel();
    }

    public UserModel(String ID, String email, String username, String password){
        this.ID = ID;
        this.email = email;
        credentials = new CredentialsModel(username, password);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public CredentialsModel getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsModel credentials) {
        this.credentials = credentials;
    }
}
