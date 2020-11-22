package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.googryandroidarchitecturestudy.databinding.FragmentRecentBinding
import com.example.googryandroidarchitecturestudy.ui.contract.RecentContract
import com.example.googryandroidarchitecturestudy.ui.presenter.BasePresenter
import com.example.googryandroidarchitecturestudy.ui.presenter.RecentPresenter

class RecentFragment : BaseFragment<FragmentRecentBinding, BasePresenter>(), RecentContract.View {
    override val presenter: RecentPresenter by lazy {
        RecentPresenter(this, movieRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecentBinding =
        FragmentRecentBinding.inflate(inflater, container, false)

    companion object {
        private val TAG = this::class.java.simpleName
    }

}