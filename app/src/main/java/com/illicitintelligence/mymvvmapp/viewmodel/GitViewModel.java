package com.illicitintelligence.mymvvmapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.illicitintelligence.mymvvmapp.model.RepoResult;
import com.illicitintelligence.mymvvmapp.network.GitRetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitViewModel extends AndroidViewModel {

    private GitRetrofitInstance retrofitInstance = new GitRetrofitInstance();

    private MutableLiveData<List<RepoResult>> resultLiveData = new MutableLiveData<>();


    public GitViewModel(@NonNull Application application) {
        super(application);
    }

    public Call<List<RepoResult>> getRepos() {
        return retrofitInstance.getRepositories();
    }

    public void getRepository(){
        retrofitInstance.getRepositories().enqueue(new Callback<List<RepoResult>>() {
            @Override
            public void onResponse(Call<List<RepoResult>> call, Response<List<RepoResult>> response) {
                resultLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<RepoResult>> call, Throwable t) {
//          Todo:
            }
        });
    }

    public MutableLiveData<List<RepoResult>> getResultLiveData(){
        return resultLiveData;
    }

}
