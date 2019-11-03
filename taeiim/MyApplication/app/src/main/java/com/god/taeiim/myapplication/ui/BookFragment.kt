package com.god.taeiim.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.api.SearchApi
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.api.provideAuthApi
import com.god.taeiim.myapplication.extensions.fromHtml
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_contents.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookFragment : Fragment() {
    private val api: SearchApi by lazy { provideAuthApi() }
    private val adapter = BookAdapter()

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
            adapter = this@BookFragment.adapter
        }

        searchBtn.setOnClickListener {
            searchEditTv.text.toString()?.let { searchBook(it) }
        }
    }

    private fun searchBook(query: String) {
        val searchCall = api.searchContents("book", query)
        searchCall?.let {
            it.enqueue(object : Callback<SearchResult> {

                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    with(adapter) {
                        response.body()?.let { setItems(it.items) }
                            ?: clearItems()
                    }
                }

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {

                }
            })
        }
    }

    private inner class BookAdapter : RecyclerView.Adapter<BookListHolder>() {
        private var resultList = ArrayList<SearchResult.Item>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListHolder =
            BookListHolder(
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

        override fun onBindViewHolder(holder: BookListHolder, position: Int) {
            resultList[position].let { book ->
                with(holder.itemView) {
                    titleTv.text = book.title.fromHtml()
                    subTitleTv.text = book.author.fromHtml()
                    descTv.text = book.description.fromHtml()

                    with(book.image) {
                        if (!this.isNullOrBlank()) {
                            thumbnailIv.visibility = View.VISIBLE
                            com.bumptech.glide.Glide.with(holder.itemView.context)
                                .load(this)
                                .into(thumbnailIv)

                        } else {
                            thumbnailIv.visibility = View.GONE
                        }
                    }
                }
                holder.link = book.link
            }
        }
    }

    private inner class BookListHolder(view: View) : RecyclerView.ViewHolder(view) {
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