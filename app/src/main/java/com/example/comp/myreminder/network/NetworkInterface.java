package com.example.comp.myreminder.network;

import com.example.comp.myreminder.model.Comment;
import com.example.comp.myreminder.model.Post;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by COMP on 25.7.2018..
 */

public interface NetworkInterface {
    Observable<List<Post>> getAllPosts();

    Observable<List<Comment>> getAllComments(int id);
}
