package com.example.mytodoapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TodoApi {

    @FormUrlEncoded
    @POST("login_app")
    // Call<RequestData> tryLogin(@Body RequestData requestData);
    Call<TodoResponse> tryLogin(@Field("InputEmail") String emailId,
                                @Field("InputPassword")String password);
}
