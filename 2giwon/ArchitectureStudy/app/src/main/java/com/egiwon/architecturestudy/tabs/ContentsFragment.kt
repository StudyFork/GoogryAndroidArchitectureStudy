package com.egiwon.architecturestudy.tabs

import com.egiwon.architecturestudy.R
import com.egiwon.architecturestudy.base.BaseFragment
import com.egiwon.architecturestudy.data.Content
import com.egiwon.architecturestudy.data.SearchCallback


class ContentsFragment(
    private val type: String
) : BaseFragment(
    R.layout.fg_contents
), SearchCallback {

    override fun onSuccess(searchContents: List<Content.Item>) {

    }
}