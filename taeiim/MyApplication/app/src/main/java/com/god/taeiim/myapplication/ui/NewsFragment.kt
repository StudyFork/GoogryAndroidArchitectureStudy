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

class NewsFragment : Fragment() {
    private val api: SearchApi by lazy { provideAuthApi() }
    private var searchCall: Call<SearchResult>? = null
    var searchResultList: ArrayList<SearchResult.Item> = ArrayList()
    private val adapter = NewsAdapter(searchResultList)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(searchResultRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@NewsFragment.adapter
        }

        searchBtn.setOnClickListener {
            val query = searchEditTv.text.toString()
            searchNews(query)
        }
    }

    private fun searchNews(query: String) {
        searchCall = api.searchContents("news", query)
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

    private inner class NewsAdapter(var resultList: ArrayList<SearchResult.Item>) :
        RecyclerView.Adapter<NewsListHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contents, parent, false)
            return NewsListHolder(view)
        }

        override fun getItemCount(): Int {
            return resultList.size
        }

        fun setItems(items: ArrayList<SearchResult.Item>) {
            resultList = items
        }

        override fun onBindViewHolder(holder: NewsListHolder, position: Int) {
            resultList[position].let { news ->
                with(holder.itemView) {
                    titleTv.text = news.title!!.fromHtml()
                    subTitleTv.text = news.pubDate!!.fromHtml()
                    descTv.text = news.description!!.fromHtml()

                    setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(news.link))) }
                }
            }
        }
    }

    private inner class NewsListHolder(view: View) : RecyclerView.ViewHolder(view)
}