package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.googryandroidarchitecturestudy.databinding.FragmentRecentBinding
import com.example.googryandroidarchitecturestudy.ui.contract.RecentContract
import com.example.googryandroidarchitecturestudy.ui.presenter.BasePresenter
import com.example.googryandroidarchitecturestudy.ui.presenter.RecentPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecentFragment : BaseFragment<FragmentRecentBinding, BasePresenter>(), RecentContract.View {
    override val presenter: RecentPresenter by lazy {
        RecentPresenter(this, movieRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        viewLifecycleOwner.lifecycleScope.launch {
            val recentSearch = presenter.getRecentSearch()
            Log.d(TAG, recentSearch.toString())
        }
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecentBinding =
        FragmentRecentBinding.inflate(inflater, container, false)

    companion object {
        private val TAG = RecentFragment::class.java.simpleName
    }

}