package com.kekui.retrofit_android_demo.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.kekui.retrofit_android_demo.model.ItemResponse;

/**
 * Created by kevin on 2017-06-23.
 */

public interface CommitApiService {

   @GET("search/commits")
    Call<ItemResponse> getReposCommits( @Header("Accept") String authorization, @Query(value="q", encoded = false) String para);


}