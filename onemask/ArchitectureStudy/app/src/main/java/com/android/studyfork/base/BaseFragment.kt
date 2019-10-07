package com.android.studyfork.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BaseContract.Presenter>(@LayoutRes private val layoutRes: Int) :
    Fragment(), BaseContract.View<P> {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onDestroy() {
        presenter.clearDisposable()
        super.onDestroy()
    }
}