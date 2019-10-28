package com.oldbie.apflux.network;

import com.oldbie.apflux.model.ServerResponse;
import com.oldbie.apflux.model.TimeTable;
import com.oldbie.apflux.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkAPI {

    @POST("api_login.php")
    @FormUrlEncoded
    Call<ServerResponse> checkLogin(@Field("user") String user,
                                    @Field("pass") String pass);

    @GET("aee9b644-52bf-4fb5-a3c1-371b1803192d")
    Call<List<TimeTable>> getAllData();

}
