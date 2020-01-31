package com.jay.architecturestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSourceImpl
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.jay.architecturestudy.util.showToastMessage
import com.jay.architecturestudy.util.then

abstract class BaseFragment<T : ViewDataBinding, VM: BaseViewModel<*>>(
    private val layoutId: Int
) : Fragment() {

    lateinit var binding: T

    abstract val viewModel: VM

    val naverSearchRepository by lazy {
        NaverSearchRepositoryImpl(
            naverSearchRemoteDataSource = NaverSearchRemoteDataSourceImpl,
            naverSearchLocalDataSource = NaverSearchLocalDataSourceImpl
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorMsg.observe(viewLifecycleOwner, Observer {
            it.isNotBlank().then {
                showErrorMessage(it)
            }
        })

        viewModel.invalidKeyword.observe(viewLifecycleOwner, Observer {
            it.then {
                context?.let { context ->
                    showErrorMessage(context.getString(R.string.warn_input_keyword))
                }
            }
        })
    }

    private fun showErrorMessage(message: String) {
        context?.showToastMessage(message)
    }
}