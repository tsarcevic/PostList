package com.example.comp.myreminder.ui.posts;

import com.example.comp.myreminder.model.Post;

import java.util.List;

/**
 * Created by COMP on 24.7.2018..
 */

public interface PostsInterface {

    interface View {

        void showPosts(List<Post> postList);

        void showErrorMessage();

        void navigateToPostComments(int id);

        void hideRefreshing();

        void showRefreshing();
    }

    interface Presenter {
        void setView(View view);

        void fetchData();

        void onItemClicked(int id);

        void onRefreshPulled();
    }
}
