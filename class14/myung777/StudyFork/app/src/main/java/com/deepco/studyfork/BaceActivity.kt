package com.deepco.studyfork

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.deepco.studyfork.presenter.BaseContract
import com.deepco.studyfork.presenter.BasePresenter

open class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseContract.View {
    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val REQ_CODE_RECENT_SEARCH = 1001
        const val EXTRA_MOVIE_TITLE = "movie_title"
    }
}