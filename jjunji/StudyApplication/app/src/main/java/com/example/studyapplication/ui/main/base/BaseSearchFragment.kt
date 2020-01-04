package com.example.studyapplication.ui.main.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.example.studyapplication.ui.base.BaseFragment

open class BaseSearchFragment(
    @LayoutRes override val fragmentId: Int)
    : BaseFragment(fragmentId),
    BaseSearchContract.View {

    private lateinit var presenter : BaseSearchContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = BaseSearchPresenter(this)
    }

    override fun toastErrorConnFailed(message: String) {

    }


}