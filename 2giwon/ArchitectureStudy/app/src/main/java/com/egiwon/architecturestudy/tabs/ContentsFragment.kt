package com.egiwon.architecturestudy.tabs

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.Tab
import com.egiwon.architecturestudy.base.BaseFragment
import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.data.source.NaverDataRepository
import com.egiwon.architecturestudy.data.source.remote.NaverRemoteDataSource
import kotlinx.android.synthetic.main.fg_contents.*

class ContentsFragment : BaseFragment(
    R.layout.fg_contents
), ContentsContract.View {

    private val presenter: ContentsContract.Presenter =
        ContentsPresenter(
            this,
            NaverDataRepository.getInstance(NaverRemoteDataSource.getInstance())

        )


    private val tab: Tab by lazy {
        arguments?.get(ARG_TYPE) as? Tab
            ?: throw IllegalArgumentException()
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

            setAdapter()
        }

        btn_search.setOnClickListener {
            context?.let {
                requestSearch()
            }
        }
    }

    private fun RecyclerView.setAdapter() {
        try {
            adapter = ContentsAdapter(tab)
            setHasFixedSize(true)
        } catch (ignore: Exception) {
        }
    }


    override fun onUpdateUi(resultList: List<Content.Item>) {
        (rv_contents.adapter as? ContentsAdapter)?.setList(resultList)
        progress_circular.visibility = View.GONE

    }

    override fun onFail(throwable: Throwable) {
        try {
            showToast(getString(R.string.callback_fail))
        } catch (exception: IllegalStateException) {
            showToast(getString(R.string.callback_empty_fail))
        }

        progress_circular.visibility = View.GONE
    }

    private fun requestSearch() {
        (presenter as? ContentsPresenter)?.loadContents(
            tab.name,
            et_search.text.toString()
        )

        progress_circular.visibility = View.VISIBLE
    }

    override fun onPause() {
        presenter.clearDisposable()
        super.onPause()
    }

    override fun onDestroy() {
        presenter.disposeDisposable()
        super.onDestroy()
    }

    companion object {
        private const val ARG_TYPE = "type"

        fun newInstance(type: Tab) =
            ContentsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_TYPE, type)
                }
            }

    }
}
