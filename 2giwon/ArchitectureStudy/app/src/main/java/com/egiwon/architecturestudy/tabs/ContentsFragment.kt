package com.egiwon.architecturestudy.tabs

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseFragment
import com.egiwon.architecturestudy.data.NaverDataRepositoryImpl
import com.egiwon.architecturestudy.data.source.local.ContentDataBase
import com.egiwon.architecturestudy.data.source.local.NaverLocalDataSource
import com.egiwon.architecturestudy.data.source.remote.NaverRemoteDataSource
import com.egiwon.architecturestudy.databinding.FgContentsBinding

class ContentsFragment : BaseFragment<FgContentsBinding, ContentsViewModel>(
    R.layout.fg_contents
) {

    override val viewModel: ContentsViewModel by viewModels {
        ContentsViewModelFactory(
            NaverDataRepositoryImpl.getInstance(
                NaverRemoteDataSource.getInstance(),
                NaverLocalDataSource.getInstance(
                    ContentDataBase.getInstance(requireContext()).contentDao()
                )
            )
        )
    }

    override fun onResume() {

        viewModel.getCacheContents(tab)
        super.onResume()
    }

    val tab: Tab by lazy {
        arguments?.get(ARG_TYPE) as? Tab
            ?: error(getString(R.string.type_is_null))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding) {
            rvContents.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )

            rvContents.adapter = ContentsAdapter(tab)

            btnSearch.setOnClickListener {
                viewModel.loadContents(tab, etSearch.text.toString())
            }
        }

        subscribeObservables()
    }

    private fun subscribeObservables() {
        viewModel.asSearchQueryListObservable()
            .subscribe({
                (binding.rvContents.adapter as? ContentsAdapter)?.replaceAll(it)
            }, {
                showErrorLoadFail()
            }).addDisposable()

        viewModel.asSearchEmptyQueryErrorObservable()
            .subscribe {
                showErrorQueryEmpty()
            }.addDisposable()

        viewModel.asShowLoadingProgressBarObservable()
            .subscribe { show ->
                if (show) showLoading() else hideLoading()
            }.addDisposable()

        viewModel.asSearchQueryResultEmptyListObservable()
            .subscribe {
                showErrorResultEmpty()
            }.addDisposable()

        viewModel.asSearchQueryObservable()
            .subscribe {
                binding.etSearch.setText(it)
            }.addDisposable()
    }

    private fun showErrorQueryEmpty() {
        showToast(getString(R.string.error_query_empty))
    }

    private fun showErrorLoadFail() {
        showToast(getString(R.string.error_load_fail))
    }

    private fun showErrorResultEmpty() {
        showToast(getString(R.string.error_empty_fail))
    }

    private fun showLoading() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressCircular.visibility = View.GONE
    }

    fun loadContentsByHistoryQuery(type: Tab, query: String) {
        viewModel.loadContentsByHistory(type, query)
    }

    companion object {
        private const val ARG_TYPE = "ARG_TYPE"

        fun newInstance(type: Tab) = ContentsFragment().apply {
            arguments = bundleOf(ARG_TYPE to type)
        }

    }
}
