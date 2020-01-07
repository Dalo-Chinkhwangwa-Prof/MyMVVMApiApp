package com.illicitintelligence.mymvvmapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.illicitintelligence.mymvvmapp.R;
import com.illicitintelligence.mymvvmapp.model.RepoResult;
import com.illicitintelligence.mymvvmapp.viewmodel.GitViewModel;

import java.util.List;

public class GitAdapter extends RecyclerView.Adapter<GitAdapter.GitViewHolder> {

    private List<RepoResult> repoResultList;
    private Context applicationContext;

    public GitAdapter(List<RepoResult> repoResultList) {
        this.repoResultList = repoResultList;
    }

    @NonNull
    @Override
    public GitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        applicationContext = parent.getContext().getApplicationContext();

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.git_item_layout, parent, false);

        return new GitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GitViewHolder holder, int position) {
        holder.mainText.setText(repoResultList.get(position).getName());

        Animation transition = AnimationUtils.loadAnimation(applicationContext, R.anim.transition_animation);
        Animation textTransition = AnimationUtils.loadAnimation(applicationContext, R.anim.text_transition_animation);
        holder.itemView.startAnimation(transition);
        holder.mainText.startAnimation(textTransition);

        Glide.with(applicationContext)
                .load(repoResultList.get(position).getOwner().getAvatarUrl())
                .into(holder.avatarImageView);
        holder.avatarImageView.startAnimation(transition);
    }

    @Override
    public int getItemCount() {
        return repoResultList.size();
    }

    class GitViewHolder extends RecyclerView.ViewHolder {

        TextView mainText;
        ImageView avatarImageView;

        public GitViewHolder(@NonNull View itemView) {
            super(itemView);
            mainText = itemView.findViewById(R.id.main_textview);
            avatarImageView = itemView.findViewById(R.id.user_avatar);
        }
    }
}
