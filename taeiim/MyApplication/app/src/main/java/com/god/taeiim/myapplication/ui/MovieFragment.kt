package com.god.taeiim.myapplication.ui

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
import kotlinx.android.synthetic.main.item_movie.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {
    val api: SearchApi by lazy { provideAuthApi() }
    internal var searchCall: Call<SearchResult>? = null
    var searchResultList: ArrayList<SearchResult.Item> = ArrayList()
    private val adapter = MovieAdapter(searchResultList)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(searchResultRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MovieFragment.adapter
        }
        searchBtn.setOnClickListener {
            val query = searchEditTv.text.toString()
            searchMovie(query)
        }
    }

    private fun searchMovie(query: String) {
        searchCall = api.searchContents("movie", query)
        searchCall!!.enqueue(object : Callback<SearchResult> {

            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                with(adapter) {
                    setItems(response.body()!!.items as ArrayList<SearchResult.Item>)
                    notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {

            }
        })
    }

    private inner class MovieAdapter(var resultList: ArrayList<SearchResult.Item>) : RecyclerView.Adapter<MovieListHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            return MovieListHolder(view)
        }

        override fun getItemCount(): Int {
            return resultList.size
        }

        fun setItems(items: ArrayList<SearchResult.Item>) {
            resultList = items
        }

        override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
            resultList[position].let { movie ->
                with(holder.itemView) {
                    titleTv.text = movie.title!!.fromHtml()
                    subTitleTv.text = movie.pubDate!!.fromHtml()
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
            }
        }
    }

    private inner class MovieListHolder(view: View) : RecyclerView.ViewHolder(view)
}