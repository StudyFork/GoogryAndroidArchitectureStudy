package com.egiwon.architecturestudy.ui.tabs

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseFragment
import com.egiwon.architecturestudy.databinding.FgContentsBinding
import com.egiwon.architecturestudy.ui.Tab
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ContentFragment : BaseFragment<FgContentsBinding, ContentViewModel>(
    R.layout.fg_contents
) {

    override val viewModel: ContentViewModel by viewModel { parametersOf(tab) }

    override fun onResume() {
        viewModel.getCacheContents()
        super.onResume()
    }

    val tab: Tab by lazy {
        arguments?.get(ARG_TYPE) as? Tab
            ?: error(getString(R.string.type_is_null))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            vm = this@ContentFragment.viewModel
            rvContents.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )

            rvContents.adapter = ContentAdapter(tab)
        }

        viewModel.isResultEmptyError.observe(viewLifecycleOwner, Observer { empty ->
            if (empty) showToast(R.string.error_empty_fail)
        })
    }


    fun loadContentsByHistoryQuery(query: String) {
        viewModel.loadContentsByHistory(query)
    }

    companion object {
        private const val ARG_TYPE = "ARG_TYPE"

        fun newInstance(type: Tab) = ContentFragment().apply {
            arguments = bundleOf(ARG_TYPE to type)
        }

    }
}
