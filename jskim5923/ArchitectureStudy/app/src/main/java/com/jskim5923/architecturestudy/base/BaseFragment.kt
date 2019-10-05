package com.jskim5923.architecturestudy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), BaseContract.View {
    @get:LayoutRes
    abstract val layoutRes: Int

    abstract val presenter: BaseContract.Presenter

    abstract override fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        presenter.clearCompositeDisposable()
        super.onDestroyView()
    }

}