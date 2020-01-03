package com.example.androidarchitecture.ui.blog


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.common.toast
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.ui.base.BaseSearchFragment
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class BlogFragment : BaseSearchFragment(R.layout.fragment_blog), BlogContract.View<BlogData> {

    private lateinit var blogAdapter: BlogAdapter
    private val presenter by lazy { BlogPresenter(this, naverSearchRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            blogAdapter = BlogAdapter()
                .also {
                    recycle.adapter = it
                    recycle.addItemDecoration(
                        DividerItemDecoration(
                            activity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
        }

        lifecycleScope.launch {
            presenter.requestSearchHist() }



        btn_search.setOnClickListener {
            presenter.requestList(edit_text.text.toString())
        }
    }


    override fun renderItems(items: List<BlogData>) {
        blogAdapter.setData(items)
    }

    override fun errorToast(msg: String?) {
        msg?.let { requireContext().toast(msg) }
    }

    override fun blankInputText() {
        requireContext().toast(getString(R.string.black_input_text))
    }


}
