package com.example.comp.myreminder.ui.comments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comp.myreminder.R;
import com.example.comp.myreminder.listeners.ItemClickListener;
import com.example.comp.myreminder.model.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP on 25.7.2018..
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsViewHolder> {

    private List<Comment> commentList = new ArrayList<>();

    public void setCommentList(List<Comment> commentList) {
        this.commentList.clear();
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View commentView = layoutInflater.inflate(R.layout.item_comment, parent, false);

        return new CommentsViewHolder(commentView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        Comment comment = commentList.get(position);

        holder.setCommentInfo(comment);
        if (position % 2 == 0) {
            holder.setWhiteBackground();
        } else {
            holder.setGrayBackground();
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}
