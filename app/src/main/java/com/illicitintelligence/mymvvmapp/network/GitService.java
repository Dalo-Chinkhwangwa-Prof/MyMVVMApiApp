package com.illicitintelligence.mymvvmapp.network;

import com.illicitintelligence.mymvvmapp.model.RepoResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitService {

//    https://www.exam[le.com/users/dalo/repos?order=date&api_key=sekj443m,ndsfjkn34n5j3kj4h3
    @GET("/users/{user_name}/{data}")
    Call<List<RepoResult>> getRepositories(
            @Path("user_name") String userName,
            @Path("data") String data,
            @Query("nonsense_Query") String apiKey,
            @Query("order") String order
    );

}
