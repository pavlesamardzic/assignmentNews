package com.pavlesamardzic.assignmentnews.ui.activity.detail

import com.pavlesamardzic.assignmentnews.data.Comment
import com.pavlesamardzic.assignmentnews.data.Post
import com.pavlesamardzic.assignmentnews.data.User

interface DetailResults {
    fun onSuccessGetPostById(post: Post)
    fun onErrorGetPostById(message: String)

    fun onSuccessGetAuthor(user: User)
    fun onErrorGetAuthor(message: String)

    fun onSuccessGetComments(comments: ArrayList<Comment>)
    fun onErrorGetComments(message: String)
}