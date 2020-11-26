package com.deepco.studyfork

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.deepco.studyfork.presenter.BaseContract

abstract class BaseActivity<presenter : BaseContract.Presenter, B : ViewDataBinding>(@LayoutRes val layoutResId: Int) :
    AppCompatActivity(), BaseContract.View {
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val REQ_CODE_RECENT_SEARCH = 1001
        const val EXTRA_MOVIE_TITLE = "movie_title"
    }
}