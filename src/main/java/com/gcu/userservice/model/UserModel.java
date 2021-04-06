package com.gcu.userservice.model;

import com.gcu.userservice.data.entity.UserEntity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserModel implements Serializable {

    private String ID;
    @NotBlank
    private String email;
    @Valid
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

    public UserModel(UserEntity user){
        ID = user.getId();
        email = user.getEmail();
        credentials = new CredentialsModel(user.getUsername(), user.getPassword());

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

    public UserEntity toEntity(){
        return new UserEntity(ID, email, credentials.getUsername(), credentials.getPassword());
    }
}
