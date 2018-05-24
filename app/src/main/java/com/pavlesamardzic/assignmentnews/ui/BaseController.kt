package com.pavlesamardzic.assignmentnews.ui

import android.util.Log

open class BaseController {
    private final val TAG: String = "ApiResponse"

    fun logSuccessResponse(){
        Log.d(TAG, "Api Success")
    }

    fun logErrorResponse(error: String){
        Log.d(TAG, "Api Error: " + error)
    }

}