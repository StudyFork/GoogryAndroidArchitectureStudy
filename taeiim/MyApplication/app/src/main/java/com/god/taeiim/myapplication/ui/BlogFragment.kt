package com.god.taeiim.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.data.NaverRepositoryImpl
import com.god.taeiim.myapplication.extensions.fromHtml
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_contents.view.*

class BlogFragment : Fragment() {
    private val adapter = BlogAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(searchResultRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@BlogFragment.adapter
        }

        searchBtn.setOnClickListener {
            searchEditTv.text.toString()?.let { searchBlog(it) }
        }
    }

    private fun searchBlog(query: String) {
        NaverRepositoryImpl.getResultData(
            "blog",
            query,
            success = { successGetSearchResult(it) },
            fail = { errorGetSearchResult() }
        )
    }

    private fun successGetSearchResult(resultList: SearchResult) {
        adapter.setItems(resultList.items)
    }

    private fun errorGetSearchResult() {
        adapter.clearItems()
        Toast.makeText(context, getString(R.string.err_search), Toast.LENGTH_SHORT).show()
    }

    private inner class BlogAdapter : RecyclerView.Adapter<BlogListHolder>() {
        private var resultList = ArrayList<SearchResult.Item>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogListHolder =
            BlogListHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_contents,
                    parent,
                    false
                )
            )

        override fun getItemCount(): Int {
            return resultList.size
        }

        fun setItems(items: List<SearchResult.Item>) {
            resultList.addAll(items)
            notifyDataSetChanged()
        }

        fun clearItems() {
            resultList.clear()
            notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: BlogListHolder, position: Int) {
            resultList[position].let { blog ->
                with(holder.itemView) {
                    titleTv.text = blog.title.fromHtml()
                    subTitleTv.text = blog.postdate.fromHtml()
                    descTv.text = blog.description.fromHtml()
                }
                holder.link = blog.link
            }
        }
    }

    private inner class BlogListHolder(view: View) : RecyclerView.ViewHolder(view) {
        var link: String? = ""

        init {
            itemView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(link)
                    )
                )
            }
        }
    }
}