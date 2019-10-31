package com.oldbie.apflux.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceAPI {

    public static String BASE_URL = "https://mangahay.net/test/";

    public static String FOLDER_USER = "api_for_user/";
    public static String FOLDER_NEWS = "api_for_news/";

    public static String URL_TIMETABLE = "https://jsonstorage.net/api/items/";
//public static String URL_TIMETABLE = "https://mangahay.net/test/api_for_user/";

    public static <S> S userService(Class<S> serviceClass) {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL + FOLDER_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient).build();

        return retrofit.create(serviceClass);

    }

    //.. TEST GET ALL USER Info ..//
    private static Retrofit getRetrofitDataUser() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL + FOLDER_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //.. TEST GET ALL NEWS Info ..//
    public static <S> S newService(Class<S> serviceClass) {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL + FOLDER_NEWS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient).build();

        return retrofit.create(serviceClass);

    }

    public static NetworkAPI getDataJSONUser(){

        return getRetrofitDataUser().create( NetworkAPI.class );
    }
}
