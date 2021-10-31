package com.example.client_chat.Model;

import java.io.Serializable;

public class User implements Serializable {
    private String ImgProfile;
    private String Username;
    private String UniqueId;
    private int Gender;
    private String birthday;

    public User() {
    }

    public User(String imgProfile, String username, String uniqueId, int gender, String birthday) {
        this.ImgProfile = imgProfile;
        this.Username = username;
        this.UniqueId = uniqueId;
        this.Gender = gender;
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

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }
}
