package com.pavlesamardzic.assignmentnews.ui.activity.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import com.pavlesamardzic.assignmentnews.R
import com.pavlesamardzic.assignmentnews.data.Comment
import com.pavlesamardzic.assignmentnews.data.Post
import com.pavlesamardzic.assignmentnews.data.User
import com.pavlesamardzic.assignmentnews.ui.BaseActivity
import com.pavlesamardzic.assignmentnews.ui.activity.main.MainActivity
import com.pavlesamardzic.assignmentnews.ui.adapter.CommentAdapter
import com.pavlesamardzic.assignmentnews.util.Utils
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(), DetailResults {
    private var postId: Int = 0
    private val detailResults: DetailResults = this
    private val detailController: DetailController = DetailController(restService, detailResults)
    private var commentsAdapter: CommentAdapter? = null

    override fun onResume() {
        super.onResume()
        makeNewTag(MainActivity::class.java as Any)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        postId = intent.extras.getInt(Utils.POST_ID)

        setToolbar()
        configureAdapter()

        detailController.callApiToGetPostById(postId)
    }

    private fun setToolbar() {
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);
    }

    private fun configureAdapter() {
        commentsAdapter = CommentAdapter(this, ArrayList<Comment>())
        this.commentsRecyclerView.layoutManager = LinearLayoutManager(this)
        this.commentsRecyclerView.adapter = commentsAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSuccessGetPostById(post: Post) {
        this.tvTitle.text = post.title
        this.tvBody.text = post.body
    }

    override fun onSuccessGetAuthor(user: User) {
        this.tvUsername.text = user.username
    }

    override fun onSuccessGetComments(comments: ArrayList<Comment>) {
        this.tvNumberOfComments.text = String.format("%s (%d)", getString(R.string.comments), comments.count());
        commentsAdapter!!.updateComments(comments)
        commentsAdapter!!.notifyDataSetChanged()
    }

    override fun onErrorGetPostById(message: String) {
        showErrorToastMessage(message)
    }

    override fun onErrorGetAuthor(message: String) {
        showErrorToastMessage(message)
    }

    override fun onErrorGetComments(message: String) {
        showErrorToastMessage(message)
    }
}
