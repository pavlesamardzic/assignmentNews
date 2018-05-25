package com.pavlesamardzic.assignmentnews.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pavlesamardzic.assignmentnews.R
import com.pavlesamardzic.assignmentnews.data.Comment
import com.pavlesamardzic.assignmentnews.ui.activity.main.PostsListResults
import kotlinx.android.synthetic.main.comment_list_row.view.*
import kotlinx.android.synthetic.main.news_list_row.view.*

class CommentAdapter(val context: Context, var comments: ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_list_row, parent, false))
    }

    fun updateComments(comments: ArrayList<Comment>){
        this.comments = comments
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment: Comment = comments.get(position)
        val body = comment.body

        holder?.commentContent?.text = body
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val commentContent = view.commentContent
    }
}