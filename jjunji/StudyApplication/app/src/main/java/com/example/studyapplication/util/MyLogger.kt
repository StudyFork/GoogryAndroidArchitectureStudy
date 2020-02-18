package com.example.studyapplication.util

import android.util.Log
import com.example.studyapplication.BuildConfig

object MyLogger {
    private const val TAG = "STUDY_APPLICATION"

    fun e(message : String) {
        if(BuildConfig.DEBUG) {
          Log.e(TAG, ">>> $message")
        }
    }

    fun e(tag : String, message : String) {
        if(BuildConfig.DEBUG) {
            Log.e(tag, ">>> $message")
        }
    }

}