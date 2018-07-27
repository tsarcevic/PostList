package com.example.comp.myreminder.ui.posts;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comp.myreminder.R;
import com.example.comp.myreminder.listeners.ItemClickListener;
import com.example.comp.myreminder.model.Post;
import com.example.comp.myreminder.network.NetworkManager;
import com.example.comp.myreminder.presenters.PostsPresenter;
import com.example.comp.myreminder.ui.comments.CommentsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsView extends AppCompatActivity implements PostsInterface.View, ItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_post_list)
    RecyclerView postList;

    @BindView(R.id.tv_no_data)
    TextView noDataText;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    PostsInterface.Presenter presenter;

    PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        setUI();

        presenter = new PostsPresenter(NetworkManager.getInstance());
        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        presenter.fetchData();

        super.onResume();
    }

    private void setUI() {
        ButterKnife.bind(this);

        swipeRefreshLayout.setOnRefreshListener(this);

        postsAdapter = new PostsAdapter();
        postsAdapter.setItemClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        postList.setLayoutManager(layoutManager);
        postList.setAdapter(postsAdapter);

        showNoDataText();
    }

    private void showNoDataText() {
        noDataText.setVisibility(View.VISIBLE);
    }

    private void hideNoDataText() {
        noDataText.setVisibility(View.GONE);
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
    public void onItemClicked(int id) {
        presenter.onItemClicked(id);
    }

    @Override
    public void showPosts(List<Post> postList) {
        postsAdapter.setPostList(postList);
        hideNoDataText();
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.internet_connection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToPostComments(int id) {
        startActivity(CommentsView.getLaunchIntent(this, id));
    }
}
