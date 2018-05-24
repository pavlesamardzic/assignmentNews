package com.pavlesamardzic.assignmentnews.network

import com.pavlesamardzic.assignmentnews.data.Comment
import com.pavlesamardzic.assignmentnews.data.Post
import com.pavlesamardzic.assignmentnews.data.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface AssignmentNewsApiService {

    @GET("posts")
    fun getPosts(): Observable<ArrayList<Post>>

    @GET("posts")
    fun getPostById(@Query("id") postId: Int): Observable<ArrayList<Post>>

    @GET("comments")
    fun getCommentsByPostId(@Query("postId") postId: Int): Observable<ArrayList<Comment>>

    @GET("users")
    fun getGetAuthorByUserId(@Query("id") userId: Int): Observable<ArrayList<User>>
}