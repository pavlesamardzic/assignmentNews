package com.pavlesamardzic.assignmentnews.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.pavlesamardzic.assignmentnews.R
import com.pavlesamardzic.assignmentnews.data.Comment
import com.pavlesamardzic.assignmentnews.data.Post
import com.pavlesamardzic.assignmentnews.ui.BaseActivity
import com.pavlesamardzic.assignmentnews.ui.activity.detail.DetailActivity
import com.pavlesamardzic.assignmentnews.ui.adapter.NewsAdapter
import com.pavlesamardzic.assignmentnews.util.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), PostsListResults {
    private val postsListResults: PostsListResults = this
    private val mainController: MainController = MainController(restService, postsListResults)
    private var newsAdapter: NewsAdapter? = null

    override fun onResume() {
        super.onResume()
        makeNewTag(MainActivity::class.java as Any)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // configiure adapter as begingin
        configureAdapter()

        // call api to get all posts
        mainController.callApiToGetPosts()
    }

    private fun configureAdapter() {
        newsAdapter = NewsAdapter(this, ArrayList<Post>(), postsListResults)
        this.newsRecyclerView.layoutManager = LinearLayoutManager(this)
        this.newsRecyclerView.adapter = newsAdapter
    }

    override fun onItemClick(post: Post) {
        var intent: Intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Utils.POST_ID, post.id)
        startActivity(intent)
    }

    override fun onSuccess(posts: ArrayList<Post>) {
        newsAdapter!!.updatePosts(posts)
        newsAdapter!!.notifyDataSetChanged()
    }

    override fun onError(message: String) {
        showErrorToastMessage(message)
    }
}
