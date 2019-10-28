package com.egiwon.architecturestudy.tabs

import android.os.Bundle
import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseFragment
import com.egiwon.architecturestudy.data.SearchCallback
import com.egiwon.architecturestudy.data.SearchService
import kotlinx.android.synthetic.main.fg_contents.*
import kotlinx.android.synthetic.main.stub_search.*

class BlogFragment : BaseFragment(
    R.layout.fg_contents
), SearchCallback {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val stub = stub_search
        stub?.inflate()

        btn_search.setOnClickListener {
            context?.let {
                SearchService(this).getMovieList(
                    et_search.text.toString(),
                    it.getString(R.string.query_type_blog),
                    it.getString(R.string.naver_api_client_id),
                    it.getString(R.string.naver_api_client_secret)
                )
            }
        }
    }

    override fun <T> onSuccess(searchContents: List<T>) {

    }
}