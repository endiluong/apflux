package com.oldbie.applux.network;

import com.oldbie.applux.model.ServerResponse;
import com.oldbie.applux.model.TimeTable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkAPI {

    @GET("api_check_user.php")
    Call<ServerResponse> checkLogin(@Query("user") String user,
                                    @Query("pass") String pass);

    @GET("aee9b644-52bf-4fb5-a3c1-371b1803192d")
    Call<List<TimeTable>> getAllData(  );

}
