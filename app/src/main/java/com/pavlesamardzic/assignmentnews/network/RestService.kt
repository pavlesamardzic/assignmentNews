package com.pavlesamardzic.assignmentnews.network

import android.provider.SyncStateContract
import com.pavlesamardzic.assignmentnews.data.Comment
import com.pavlesamardzic.assignmentnews.data.Post
import com.pavlesamardzic.assignmentnews.data.User
import com.pavlesamardzic.assignmentnews.util.Utils
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestService {
    private val assignmentNewsApiService: AssignmentNewsApiService

    init {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create())
                .baseUrl(Utils.BASE_URL)
                .build()

        assignmentNewsApiService = retrofit.create(AssignmentNewsApiService::class.java)
    }


    fun getPosts(): Observable<ArrayList<Post>>{
        return assignmentNewsApiService.getPosts()
    }

    fun getPostById(postId: Int): Observable<ArrayList<Post>>{
        return assignmentNewsApiService.getPostById(postId)
    }

    fun getCommentsByPostId(postId: Int): Observable<ArrayList<Comment>>{
        return assignmentNewsApiService.getCommentsByPostId(postId)
    }

    fun getGetAuthorByUserId(userId: Int): Observable<ArrayList<User>>{
        return assignmentNewsApiService.getGetAuthorByUserId(userId)
    }
}