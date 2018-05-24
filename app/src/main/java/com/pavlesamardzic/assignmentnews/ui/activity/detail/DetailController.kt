package com.pavlesamardzic.assignmentnews.ui.activity.detail

import android.util.Log
import com.pavlesamardzic.assignmentnews.data.Comment
import com.pavlesamardzic.assignmentnews.data.Post
import com.pavlesamardzic.assignmentnews.data.User
import com.pavlesamardzic.assignmentnews.network.RestService
import com.pavlesamardzic.assignmentnews.ui.BaseController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailController(private val restService: RestService,
                       private val detailResults: DetailResults) : BaseController() {

    fun callApiToGetPostById(postId: Int){
                restService.getPostById(postId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResultPost(result[0]) },
                                { error -> showError(error.message) }
                        )

    }

    private fun callApiToGetCommentsByPostId(postId: Int){
                restService.getCommentsByPostId(postId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResultComment(result) },
                                { error -> showError(error.message) }
                        )

    }

    private fun callApiToGetAuthorByUserId(userId: Int){
                restService.getGetAuthorByUserId(userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResultUser(result[0]) },
                                { error -> showError(error.message) }
                        )

    }

    private fun showResultComment(result: ArrayList<Comment>?) {
        logSuccessResponse()
        Log.d("RES-COMMENT", result!!.count().toString())
        detailResults.onSuccessGetComments(result)
    }

    private fun showResultUser(result: User?) {
        logSuccessResponse()
        Log.d("RES-USER",result!!.username)
        detailResults.onSuccessGetAuthor(result)
    }

    private fun showResultPost(result: Post) {
        logSuccessResponse()
        Log.d("RES-POST", result!!.id.toString())

        // update post on ui
        detailResults.onSuccessGetPostById(result)

        // call api to get all comments by postId
        callApiToGetCommentsByPostId(result.id)

        // call api to get author by userId
        callApiToGetAuthorByUserId(result.userId)
    }

    private fun showError(message: String?){
        logErrorResponse(message!!)
        detailResults.onErrorGetPostById(message)
    }

}