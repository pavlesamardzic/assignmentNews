package com.pavlesamardzic.assignmentnews.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pavlesamardzic.assignmentnews.R
import com.pavlesamardzic.assignmentnews.data.Post
import com.pavlesamardzic.assignmentnews.ui.activity.main.PostsListResults
import kotlinx.android.synthetic.main.news_list_row.view.*

class NewsAdapter(val context: Context, var posts: ArrayList<Post>, val postsListResults: PostsListResults) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_list_row, parent, false))
    }

    fun updatePosts(posts: ArrayList<Post>){
        this.posts = posts
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: Post = posts.get(position);
        val title = post.title

        holder?.postTitle?.text = title
        holder.mView.setOnClickListener { postsListResults.onItemClick(post) }
        // todo: add image later
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val mView = view;
        val postTitle = view.postTitle
        val userImage = view.userImage
    }
}