package com.oldbie.applux.network;

import com.oldbie.applux.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkAPI {

    @GET("api_check_user.php")
    Call<ServerResponse> checkLogin(@Query("user") String user,
                                    @Query("pass") String pass);

}