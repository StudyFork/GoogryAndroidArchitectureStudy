package com.project.architecturestudy.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.architecturestudy.R
import com.project.architecturestudy.components.parseHTMLTag
import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.room.MovieLocal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class SearchAdapter : RecyclerView.Adapter<SearchingResultHolder>() {

    private val searchList: MutableList<Movie.Items> = mutableListOf()
    private val savedLocalList: MutableList<MovieLocal> = mutableListOf()
    var onClick: ((Movie.Items) -> Unit)? = null
    private var isCaching = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchingResultHolder {
        return SearchingResultHolder(parent).apply {
            itemView.setOnClickListener {
                if (!isCaching) {
                    val item = searchList[adapterPosition]
                    onClick?.invoke(item)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: SearchingResultHolder, position: Int) {
        when (isCaching) {
            true -> holder.localBind(savedLocalList[position])
            false -> holder.remoteBind(searchList[position])
        }
    }

    override fun getItemCount(): Int {
        return when (isCaching) {
            true -> savedLocalList.count()
            false -> searchList.count()
        }
    }

    fun resetData(search: ArrayList<Movie.Items>) {
        searchList.clear()
        savedLocalList.clear()
        isCaching = false
        searchList.addAll(search)
        notifyDataSetChanged()
    }

    fun setLocalData(savedResultList: ArrayList<MovieLocal>) {
        searchList.clear()
        savedLocalList.clear()
        isCaching = true
        savedLocalList.addAll(savedResultList)
        Log.d("bsjbsjadapter", savedLocalList[0].image)
        Log.d("bsjbsjadapter", savedLocalList[1].image)
        Log.d("bsjbsjadapter", savedLocalList[2].image)
        Log.d("bsjbsjadapter", savedLocalList[3].image)
        Log.d("bsjbsjadapter", savedLocalList[4].image)

        notifyDataSetChanged()
    }
}

class SearchingResultHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
) {
    fun remoteBind(search: Movie.Items) {
        when (search.image.isEmpty()) {
            true -> itemView.iv_movie.setImageResource(R.drawable.ic_no_resource)
            false -> Picasso.get()
                .load(search.image)
                .placeholder(R.drawable.ic_no_resource)
                .centerCrop()
                .fit()
                .into(itemView.iv_movie)
        }
        itemView.tv_title.text = search.title.parseHTMLTag()
        itemView.tv_subTitle.text = search.subtitle.parseHTMLTag()
        itemView.tv_pubDate.text = search.pubDate.parseHTMLTag()
        itemView.tv_actors.text = search.actor.parseHTMLTag()
    }

    fun localBind(search: MovieLocal) {
        when (search.image.isEmpty()) {
            true -> itemView.iv_movie.setImageResource(R.drawable.ic_no_resource)
            false -> Picasso.get()
                .load(search.image)
                .placeholder(R.drawable.ic_no_resource)
                .centerCrop()
                .fit()
                .into(itemView.iv_movie)
        }
        itemView.tv_title.text = search.title.parseHTMLTag()
        itemView.tv_subTitle.text = search.subtitle.parseHTMLTag()
        itemView.tv_pubDate.text = search.pubDate.parseHTMLTag()
        itemView.tv_actors.text = search.actor.parseHTMLTag()
    }
}