package com.example.seonoh.seonohapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle

class ProgressLoadingDialog(context: Context) : Dialog(context,R.style.AppTheme_TransparentActivity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setLayout(android.view.WindowManager.LayoutParams.MATCH_PARENT, android.view.WindowManager.LayoutParams.MATCH_PARENT)
        setContentView(R.layout.dialog_progress)


    }
}