package com.example.comp.myreminder.network;

import com.example.comp.myreminder.model.Comment;
import com.example.comp.myreminder.model.Post;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by COMP on 25.7.2018..
 */

public class NetworkManager implements NetworkInterface {

    private static NetworkManager networkManager;

    private final ApiService apiService;

    public NetworkManager() {
        apiService = BackendFactory.getApiServiceInstance();
    }

    public static NetworkManager getInstance() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }

        return networkManager;
    }

    @Override
    public Observable<List<Post>> getAllPosts() {
        return apiService.getAllPosts();
    }

    @Override
    public Observable<List<Comment>> getAllComments(int id) {
        return apiService.getPostComments(id);
    }
}
