package com.illicitintelligence.mymvvmapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.mymvvmapp.R;
import com.illicitintelligence.mymvvmapp.adapter.GitAdapter;
import com.illicitintelligence.mymvvmapp.model.RepoResult;
import com.illicitintelligence.mymvvmapp.viewmodel.GitViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private GitViewModel viewModel;

    @BindView(R.id.git_recycerview)
    RecyclerView recyclerView;

    Observer<List<RepoResult>> myObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders
                .of(this).get(GitViewModel.class);

        myObserver = new Observer<List<RepoResult>>() {
            @Override
            public void onChanged(List<RepoResult> repoResults) {

                setupRV(repoResults);
            }
        };
        viewModel.getResultLiveData().observe(MainActivity.this, myObserver);
        viewModel.getRepository();
    }

    private void setupRV(List<RepoResult> response) {
        GitAdapter adapter = new GitAdapter(response);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
