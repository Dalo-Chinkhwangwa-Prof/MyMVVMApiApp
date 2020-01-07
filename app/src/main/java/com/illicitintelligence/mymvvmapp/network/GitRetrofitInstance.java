package com.illicitintelligence.mymvvmapp.network;

import com.illicitintelligence.mymvvmapp.model.RepoResult;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitRetrofitInstance {


    private final String BASE_URL = "https://api.github.com";

    private GitService gitService;

    public GitRetrofitInstance() {
        gitService = createGitService(getInstance());
    }



    private Retrofit getInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        return new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private GitService createGitService(Retrofit retrofit) {
        return retrofit.create(GitService.class);
    }

    public Call<List<RepoResult>> getRepositories() {
        return gitService.getRepositories(
                "Dalo-Chinkhwangwa-Prof",
                "repos", "esfsjljskfjs", "date");
    }
}
