package com.example.bulbul.restapi.Interface;

/**
 * Created by bulbul on 4/16/2018.
 */

import com.example.bulbul.restapi.Model.ServerResponse;
import com.example.bulbul.restapi.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("/retrofit_get_post/server_side_code.php")
    Call<ServerResponse> getUserValidity(@Body User userLoginCredential);

    @GET("/retrofit_get_post/server_side_code.php")
    Call<ServerResponse> getJoke(@Query("user_id") String userId);

}
