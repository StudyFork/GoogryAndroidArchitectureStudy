package com.architecture.androidarchitecturestudy.ui.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BaseContract.Presenter> : AppCompatActivity(), BaseContract.View {
    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val REQ_CODE_SEARCH_HISTORY = 101
        const val MOVIE_KEYWORD = "movie_keyword"
    }
}