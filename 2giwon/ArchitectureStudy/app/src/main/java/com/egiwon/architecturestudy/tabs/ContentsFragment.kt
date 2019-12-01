package com.egiwon.architecturestudy.tabs

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseFragment
import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.data.source.NaverDataRepository
import com.egiwon.architecturestudy.data.source.remote.NaverRemoteDataSource
import kotlinx.android.synthetic.main.fg_contents.*

class ContentsFragment : BaseFragment<ContentsContract.Presenter>(
    R.layout.fg_contents
), ContentsContract.View {

    override val presenter: ContentsContract.Presenter = ContentsPresenter(
        this,
        NaverDataRepository.getInstance(
            NaverRemoteDataSource.getInstance()

        )
    )

    private val tab: Tab by lazy {
        arguments?.get(ARG_TYPE) as? Tab
            ?: error(getString(R.string.type_is_null))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(rv_contents) {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )

            adapter = ContentsAdapter(tab)
            setHasFixedSize(true)
        }

        btn_search.setOnClickListener {
            presenter.loadContents(tab, et_search.text.toString())
        }
    }

    override fun showQueryResult(resultList: List<Content.Item>) {
        (rv_contents.adapter as? ContentsAdapter)?.setList(resultList)
    }

    override fun showErrorQueryEmpty() {
        showToast(getString(R.string.error_query_empty))
    }

    override fun showErrorLoadFail() {
        showToast(getString(R.string.error_load_fail))
    }

    override fun showErrorResultEmpty() {
        showToast(getString(R.string.error_empty_fail))
    }

    override fun showLoading() {
        progress_circular.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_circular.visibility = View.GONE
    }

    companion object {
        private const val ARG_TYPE = "ARG_TYPE"

        fun newInstance(type: Tab) = ContentsFragment().apply {
            arguments = bundleOf(ARG_TYPE to type)
        }

    }
}
