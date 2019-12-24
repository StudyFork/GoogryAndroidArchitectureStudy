package com.example.androidarchitecture.ui.blog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androidarchitecture.R
import com.example.androidarchitecture.common.toast
import com.example.androidarchitecture.data.repository.NaverRepoImpl
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.ui.base.BaseContract
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class BlogFragment : Fragment(), BaseContract.View<BlogData> {

    private lateinit var blogAdapter: BlogAdapter
    private val presenter by lazy { BlogPresenter(this, NaverRepoImpl) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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


}
