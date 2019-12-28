package com.example.architecturestudy.ui.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.BlogItem
import kotlinx.android.synthetic.main.fragment_blog.*

class BlogFragment : Fragment(), BlogContract.View {

    private lateinit var blogAdapter: BlogAdapter

    private val presenter : BlogContract.Presenter by lazy {
        BlogPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         blogAdapter = BlogAdapter()

        recycleview.apply {
            adapter = blogAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            )
        }

        btn_search.setOnClickListener {
            if(input_text != null) {
                val edit = edit_text.text.toString()
                searchBlogList(edit)
            }
        }
    }

    private fun searchBlogList(keyWord: String) {
        presenter.taskSearch(keyWord)
    }

    override fun showErrorMessage(message: String) {
        presenter.taskError(error(message))
    }

    override fun showResult(item: List<BlogItem>) {
        blogAdapter.update(item)
    }
}