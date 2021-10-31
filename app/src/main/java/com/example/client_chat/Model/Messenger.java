package com.example.client_chat.Model;

import java.io.Serializable;

public class Messenger implements Serializable {
    private String ImgProfile;
    private String Username;
    private String Message;
    private String UniqueId;
    private String birthday;
    private int Gender;
    private int Type;

    public Messenger() {
    }

    public Messenger(String imgProfile, String uniqueId, String username, String message, int Gender, int Type, String birthday) {
        this.ImgProfile = imgProfile;
        this.Username = username;
        this.Message = message;
        this.UniqueId = uniqueId;
        this.Type = Type;
        this.Gender = Gender;
        this.birthday = birthday;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImgProfile() {
        return ImgProfile;
    }

    public void setImgProfile(String imgProfile) {
        ImgProfile = imgProfile;
    }

    public int getType() {
        return Type;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }
}
