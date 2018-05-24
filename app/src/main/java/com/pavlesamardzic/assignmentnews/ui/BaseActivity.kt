package com.pavlesamardzic.assignmentnews.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.pavlesamardzic.assignmentnews.R
import com.pavlesamardzic.assignmentnews.network.RestService

open class BaseActivity : AppCompatActivity() {
    var TAG: String = BaseActivity::class.java.simpleName
    val restService by lazy { RestService() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Started")
    }

    fun makeNewTag(clazz: Any) {
        TAG = clazz::class.java.simpleName
    }

    fun showErrorToastMessage(message: String){
        val defaultMessage: String = getString(R.string.default_error)
        Toast.makeText(this, defaultMessage + "\n" + message, Toast.LENGTH_LONG).show()
    }
}
