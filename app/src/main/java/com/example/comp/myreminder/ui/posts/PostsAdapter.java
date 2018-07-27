package com.example.comp.myreminder.ui.posts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comp.myreminder.R;
import com.example.comp.myreminder.listeners.ItemClickListener;
import com.example.comp.myreminder.model.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP on 25.7.2018..
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsViewHolder> {

    private List<Post> postList = new ArrayList<>();

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setPostList(List<Post> postList) {
        this.postList.clear();
        this.postList = postList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View postView = layoutInflater.inflate(R.layout.item_post, parent, false);

        return new PostsViewHolder(postView, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        Post post = postList.get(position);

        holder.setPostInfo(post);
        if (position % 2 == 0) {
            holder.setWhiteBackground();
        } else {
            holder.setGrayBackGround();
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
