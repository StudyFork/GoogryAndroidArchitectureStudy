package com.egiwon.architecturestudy.tabs

import android.os.Bundle
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

        btn_search.setOnClickListener {
            context?.let {
                SearchService(this).getContentsList(
                    et_search.text.toString(),
                    type,
                    it.getString(R.string.naver_api_client_id),
                    it.getString(R.string.naver_api_client_secret)
                )
            }
        }
    }

    override fun onSuccess(searchContents: List<Content.Item>) {
        val contentsAdapter = ContentsAdapter()
        contentsAdapter.setList(searchContents)

        rv_contents.adapter = contentsAdapter
        rv_contents.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        rv_contents.setHasFixedSize(true)
        (rv_contents.adapter)?.notifyItemInserted(searchContents.size - 1)
    }
}