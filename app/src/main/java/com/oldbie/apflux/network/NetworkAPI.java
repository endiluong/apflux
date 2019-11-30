package com.oldbie.apflux.network;

import com.oldbie.apflux.model.ResponseMark;
import com.oldbie.apflux.model.ResponseNews;
import com.oldbie.apflux.model.ResponseTimeTable;
import com.oldbie.apflux.model.ServerResponse;
import com.oldbie.apflux.model.TimeTable;
import com.oldbie.apflux.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkAPI {

    @POST("api_login.php")
    @FormUrlEncoded
    Call<ServerResponse> checkLogin(@Field("email") String email);

    @POST("api_get_student.php")
    @FormUrlEncoded
    Call<ServerResponse> getStudent(@Field("email") String email,
                                    @Field("token") String token);

    @POST("api_get_time_table.php")
    @FormUrlEncoded
    Call<ResponseTimeTable> getAllData(@Field("sid") String id,
                                       @Field("token") String token);

    @POST("api_get_mark.php")
    @FormUrlEncoded
    Call<ResponseMark> getMark(@Field("sid") String id,
                               @Field( "token" ) String token);

    @GET("api_get_news.php")
    Call<ResponseNews> getNewData(@Query( "index" ) int index);

}
