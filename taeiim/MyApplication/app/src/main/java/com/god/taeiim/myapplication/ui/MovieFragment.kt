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
import com.bumptech.glide.Glide
import com.god.taeiim.myapplication.R
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.data.NaverRepositoryImpl
import com.god.taeiim.myapplication.extensions.fromHtml
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_contents.view.*

class MovieFragment : Fragment() {
    private val adapter = MovieAdapter()

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
            adapter = this@MovieFragment.adapter
        }
        searchBtn.setOnClickListener {
            searchEditTv.text.toString()?.let { searchMovie(it) }
        }
    }

    private fun searchMovie(query: String) {
        NaverRepositoryImpl.getResultData(
            "movie",
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

    private inner class MovieAdapter : RecyclerView.Adapter<MovieListHolder>() {
        private var resultList = ArrayList<SearchResult.Item>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder =
            MovieListHolder(
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

        override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
            resultList[position].let { movie ->
                with(holder.itemView) {
                    titleTv.text = movie.title.fromHtml()
                    subTitleTv.text = movie.pubDate.fromHtml()
                    descTv.text = (movie.director + movie.actor).fromHtml()

                    with(movie.image) {
                        if (!this.isNullOrBlank()) {
                            thumbnailIv.visibility = View.VISIBLE
                            Glide.with(holder.itemView.context)
                                .load(this)
                                .into(thumbnailIv)

                        } else {
                            thumbnailIv.visibility = View.GONE
                        }
                    }
                }
                holder.link = movie.link
            }
        }
    }

    private inner class MovieListHolder(view: View) : RecyclerView.ViewHolder(view) {
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