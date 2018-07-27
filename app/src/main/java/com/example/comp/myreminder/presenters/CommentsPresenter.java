package com.example.comp.myreminder.presenters;

import com.example.comp.myreminder.model.Comment;
import com.example.comp.myreminder.network.NetworkInterface;
import com.example.comp.myreminder.ui.comments.CommentsInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by COMP on 25.7.2018..
 */

public class CommentsPresenter implements CommentsInterface.Presenter {

    private CommentsInterface.View view;

    private NetworkInterface networkInterface;

    private int id;

    public CommentsPresenter(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }

    @Override
    public void setView(CommentsInterface.View view) {
        this.view = view;
    }

    @Override
    public void onRefreshPulled() {
        view.showRefreshing();
        fetchData();
    }

    @Override
    public void sendId(int id) {
        if (id != -1) {
            this.id = id;
            fetchData();
        }
    }

    @Override
    public void fetchData() {
        getObservable().subscribeWith(getObserver());
    }

    private Observable<List<Comment>> getObservable() {
        return networkInterface.getAllComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<List<Comment>> getObserver() {
        return new DisposableObserver<List<Comment>>() {
            @Override
            public void onNext(List<Comment> comments) {
                view.hideRefreshing();
                if (!comments.isEmpty()) {
                    view.showComments(comments);
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
}
