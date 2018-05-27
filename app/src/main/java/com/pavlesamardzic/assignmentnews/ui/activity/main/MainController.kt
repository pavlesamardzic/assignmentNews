package com.pavlesamardzic.assignmentnews.ui.activity.main

import com.pavlesamardzic.assignmentnews.data.Post
import com.pavlesamardzic.assignmentnews.data.User
import com.pavlesamardzic.assignmentnews.network.RestService
import com.pavlesamardzic.assignmentnews.ui.BaseController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainController(private val restService: RestService,
                     private val postsListResults: PostsListResults) : BaseController() {

    private var listOfPosts: ArrayList<Post> = ArrayList<Post>()

    fun callApiToGetPosts(){
                restService.getPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> proceed(result) },
                                { error -> showError(error.message) }
                        )

    }

    private fun callApiToGetAuthorByUserId(post: Post){
        restService.getGetAuthorByUserId(post.userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> popPostListOnUi(post, result[0]) },
                        { error -> showError(error.message) }
                )

    }

    private fun popPostListOnUi(post: Post, user: User) {
        post.userEmail = user.email
        listOfPosts.add(post)

        postsListResults.onSuccess(listOfPosts)
    }

    private fun proceed(result: ArrayList<Post>?) {
        logSuccessResponse()

        for (r in result!!){
            callApiToGetAuthorByUserId(r)
        }

    }

    private fun showError(message: String?){
        logErrorResponse(message!!)
        postsListResults.onError(message)
    }

}