package com.example.bulbul.restapi.Model;

/**
 * Created by bulbul on 4/17/2018.
 */
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("password")
    private String password;

    public User(){}

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
