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
import com.bumptech.glide.Glide
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

class MovieFragment : Fragment() {
    private val api: SearchApi by lazy { provideAuthApi() }
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
        val searchCall = api.searchContents("movie", query)
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
                    subTitleTv.text = movie.author.fromHtml()
                    descTv.text = movie.description.fromHtml()

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