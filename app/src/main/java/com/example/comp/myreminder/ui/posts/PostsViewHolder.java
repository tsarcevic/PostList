package com.example.comp.myreminder.ui.posts;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.comp.myreminder.R;
import com.example.comp.myreminder.listeners.ItemClickListener;
import com.example.comp.myreminder.model.Post;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by COMP on 25.7.2018..
 */

public class PostsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_post_title)
    TextView postTitle;

    @BindView(R.id.tv_post_body)
    TextView postBody;

    @BindView(R.id.ll_root)
    View rootView;

    private int id;

    private ItemClickListener itemClickListener;

    public PostsViewHolder(View postView, ItemClickListener itemClickListener) {
        super(postView);

        ButterKnife.bind(this, postView);

        this.itemClickListener = itemClickListener;
    }

    public void setPostInfo(Post post) {
        this.id = post.getId();

        if (post != null) {
            postTitle.setText(String.format("Title:\n%s", post.getTitle()));
            postBody.setText(String.format("Body:\n%s", post.getBody()));
        }
    }

    @OnClick
    public void onPostClicked() {
        if (itemClickListener != null) {
            itemClickListener.onItemClicked(id);
        }
    }

    public void setWhiteBackground() {
        rootView.setBackgroundColor(Color.WHITE);
    }

    public void setGrayBackGround() {
        rootView.setBackgroundColor(Color.LTGRAY);
    }
}
