package com.pavlesamardzic.assignmentnews.ui.activity.main

import com.pavlesamardzic.assignmentnews.data.Post
import com.pavlesamardzic.assignmentnews.network.RestService
import com.pavlesamardzic.assignmentnews.ui.BaseController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainController(private val restService: RestService,
                     private val postsListResults: PostsListResults) : BaseController() {

    fun callApiToGetPosts(){
                restService.getPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResult(result) },
                                { error -> showError(error.message) }
                        )

    }

    private fun showResult(result: ArrayList<Post>?) {
        logSuccessResponse()
        postsListResults.onSuccess(result!!)
    }

    private fun showError(message: String?){
        logErrorResponse(message!!)
        postsListResults.onError(message)
    }

}