package com.example.comp.myreminder.network;

import com.example.comp.myreminder.model.Comment;
import com.example.comp.myreminder.model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by COMP on 25.7.2018..
 */

public interface ApiService {

    @GET("posts")
    Observable<List<Post>> getAllPosts();

    @GET("comments")
    Observable<List<Comment>> getPostComments(@Query("postId") int postId);
}
