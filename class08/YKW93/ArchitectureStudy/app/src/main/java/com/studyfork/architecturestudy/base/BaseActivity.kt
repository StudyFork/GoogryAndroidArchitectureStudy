package com.studyfork.architecturestudy.base

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseContract.View {

    abstract val presenter: P
    abstract val progressBar: ProgressBar

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    override fun setLoadingVisible(visible: Boolean) {
        if (visible) {
            pb_loading_view.visibility = View.VISIBLE
        } else {
            pb_loading_view.visibility = View.GONE
        }
    }
}