package com.example.comp.myreminder.ui.comments;

import com.example.comp.myreminder.model.Comment;

import java.util.List;

/**
 * Created by COMP on 25.7.2018..
 */

public interface CommentsInterface {

    interface View {

        void showComments(List<Comment> comments);

        void showErrorMessage();

        void showRefreshing();

        void hideRefreshing();
    }

    interface Presenter {
        void setView(View view);

        void sendId(int id);

        void fetchData();

        void onRefreshPulled();
    }
}
