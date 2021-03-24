package com.example.assignment4.networking;

import com.example.assignment4.models.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api")
    Call<UsersResponse> getSingleUser();

    @GET("api")
    Call<UsersResponse> getMultipleUser(@Query("results") int count);

}
