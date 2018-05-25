package com.pavlesamardzic.assignmentnews.ui.activity.main

import com.pavlesamardzic.assignmentnews.data.Comment
import com.pavlesamardzic.assignmentnews.data.Post

interface PostsListResults {
    fun onSuccess(posts: ArrayList<Post>)
    fun onError(message: String)
    fun onItemClick(post: Post)

}