package com.pavlesamardzic.assignmentnews

import android.app.Application
import android.util.Log

class AssignmentApp: Application(){
    private val TAG: String = AssignmentApp::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "App strated!")
    }
}