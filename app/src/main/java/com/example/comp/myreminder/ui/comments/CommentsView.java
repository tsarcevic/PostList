package com.example.comp.myreminder.ui.comments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.myreminder.R;
import com.example.comp.myreminder.model.Comment;
import com.example.comp.myreminder.network.NetworkManager;
import com.example.comp.myreminder.presenters.CommentsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by COMP on 25.7.2018..
 */

public class CommentsView extends AppCompatActivity implements CommentsInterface.View, SwipeRefreshLayout.OnRefreshListener {

    private static String KEY_POST_ID = "post_id";

    @BindView(R.id.rv_comment_list)
    RecyclerView commentList;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.tv_no_data)
    TextView noDataText;

    CommentsInterface.Presenter presenter;

    CommentsAdapter commentsAdapter;

    public static Intent getLaunchIntent(Context from, int id) {
        Intent intent = new Intent(from, CommentsView.class);
        intent.putExtra(KEY_POST_ID, id);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        setUI();

        presenter = new CommentsPresenter(NetworkManager.getInstance());
        presenter.setView(this);

        getExtras();
    }

    @Override
    protected void onResume() {
        presenter.fetchData();

        super.onResume();
    }

    private void setUI() {
        ButterKnife.bind(this);

        swipeRefreshLayout.setOnRefreshListener(this);

        commentsAdapter = new CommentsAdapter();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        commentList.setLayoutManager(layoutManager);
        commentList.setAdapter(commentsAdapter);
    }

    private void getExtras() {
        if (getIntent().hasExtra(KEY_POST_ID)) {
            presenter.sendId(getIntent().getIntExtra(KEY_POST_ID, -1));
        }
    }

    @Override
    public void onRefresh() {
        presenter.onRefreshPulled();
    }

    @Override
    public void showRefreshing() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showComments(List<Comment> comments) {
        commentsAdapter.setCommentList(comments);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, getString(R.string.internet_connection_error), Toast.LENGTH_SHORT).show();
    }
}
