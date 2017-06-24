package com.kekui.retrofit_android_demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kekui.retrofit_android_demo.model.Item_;

import com.kekui.retrofit_android_demo.R;

import java.util.List;

/**
 * Created by kevin on 2017-06-23.
 */

public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.CommitViewHolder> {

    private List<Item_> commits;
    private int rowLayout;
    private Context context;

    public CommitsAdapter(List<Item_> commits, int rowLayout, Context context) {
        this.commits = commits;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID

    public static class CommitViewHolder extends RecyclerView.ViewHolder {
        LinearLayout commitLayout;
        TextView person;
        TextView commitUrl;
        TextView commitMessage;

        public CommitViewHolder(View v) {
            super(v);
            commitLayout = (LinearLayout) v.findViewById(R.id.commit_layout);
            person = (TextView) v.findViewById(R.id.person);
            commitUrl = (TextView) v.findViewById(R.id.commit_url);
            commitMessage = (TextView) v.findViewById(R.id.commit_message);
        }
    }


    @Override
    public CommitsAdapter.CommitViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CommitViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CommitViewHolder holder, final int position) {
        //Picasso : A powerful image downloading and caching library for Android
//        Picasso.with(context)
//                .load(image_url)
//                .placeholder(android.R.drawable.sym_def_app_icon)
//                .error(android.R.drawable.sym_def_app_icon)
//                .into(holder.image);
        holder.person.setText(commits.get(position).getCommit().getAuthor().getName());
        holder.commitUrl.setText("Commit: " + commits.get(position).getCommit().getUrl());
        holder.commitMessage.setText(commits.get(position).getCommit().getMessage());
    }

    @Override
    public int getItemCount() {
        return commits.size();
    }
}

