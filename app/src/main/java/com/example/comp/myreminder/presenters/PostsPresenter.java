package com.example.comp.myreminder.presenters;

import com.example.comp.myreminder.model.Post;
import com.example.comp.myreminder.network.NetworkInterface;
import com.example.comp.myreminder.ui.posts.PostsInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by COMP on 24.7.2018..
 */

public class PostsPresenter implements PostsInterface.Presenter {

    private PostsInterface.View view;

    private NetworkInterface networkInterface;

    public PostsPresenter(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }

    @Override
    public void setView(PostsInterface.View view) {
        this.view = view;
    }

    @Override
    public void onRefreshPulled() {
        fetchData();
        view.showRefreshing();
    }

    @Override
    public void fetchData() {
        getObservable().subscribeWith(getObserver());
    }

    private Observable<List<Post>> getObservable() {
        return networkInterface.getAllPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<List<Post>> getObserver() {
        return new DisposableObserver<List<Post>>() {
            @Override
            public void onNext(List<Post> postList) {
                if (!postList.isEmpty()) {
                    view.showPosts(postList);
                    view.hideRefreshing();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.showErrorMessage();
                view.hideRefreshing();
            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void onItemClicked(int id) {
        view.navigateToPostComments(id);
    }
}
