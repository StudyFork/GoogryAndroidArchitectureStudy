package com.egiwon.architecturestudy.tabs

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseFragment
import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.data.SearchCallback
import com.egiwon.architecturestudy.data.SearchService
import kotlinx.android.synthetic.main.fg_contents.*


class ContentsFragment(
    private val type: String
) : BaseFragment(
    R.layout.fg_contents
), SearchCallback {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_contents.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        btn_search.setOnClickListener {
            context?.let {
                requestSearch()
            }
        }
    }

    private fun requestSearch() {
        SearchService(this).getContentsList(
            et_search.text.toString(),
            type
        )

        progress_circular.visibility = View.VISIBLE
    }

    override fun onSuccess(searchContents: List<Content.Item>) {
        val contentsAdapter = ContentsAdapter(type)
        contentsAdapter.setList(searchContents)

        rv_contents.adapter = contentsAdapter

        rv_contents.setHasFixedSize(true)
        (rv_contents.adapter)?.notifyItemInserted(searchContents.size - 1)
        progress_circular.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        progress_circular.visibility = View.GONE
    }

}