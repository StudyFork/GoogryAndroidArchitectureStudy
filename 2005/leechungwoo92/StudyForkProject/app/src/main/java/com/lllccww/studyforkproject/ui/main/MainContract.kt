package com.lllccww.studyforkproject.ui.main

import android.content.Context
import android.widget.Toast
import com.lllccww.studyforkproject.data.model.MovieItem

interface MainContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun closeKeyboard()
        fun showToastMessage(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    interface Presenter {
        fun getSearchMovie(movieData: List<MovieItem>)
    }
}
