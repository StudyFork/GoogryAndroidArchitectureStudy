package com.project.architecturestudy.adapters

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

    private val remoteSearchList: MutableList<Movie.Items> = mutableListOf()
    private val localSearchList: MutableList<MovieLocal> = mutableListOf()
    lateinit var onClick: ((Movie.Items) -> Unit)
    private var isCaching = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchingResultHolder {
        return SearchingResultHolder(parent).apply {
            itemView.setOnClickListener {
                val item = remoteSearchList[adapterPosition]
                onClick.invoke(item)
            }
        }
    }

    override fun onBindViewHolder(holder: SearchingResultHolder, position: Int) {
        when (isCaching) {
            true -> holder.localBind(localSearchList[position])
            false -> holder.remoteBind(remoteSearchList[position])
        }
    }

    override fun getItemCount(): Int {
        return when (isCaching) {
            true -> localSearchList.count()
            false -> remoteSearchList.count()
        }
    }

    fun setRemoteMovieData(search: ArrayList<Movie.Items>) {
        itemClear()
        isCaching = false
        this.remoteSearchList.addAll(search)
        notifyDataSetChanged()
    }

    fun setLocalMovieData(localSearchList: ArrayList<MovieLocal>) {
        itemClear()
        isCaching = true
        this.localSearchList.addAll(localSearchList)
        notifyDataSetChanged()
    }

    fun itemClear() {
        this.remoteSearchList.clear()
        this.localSearchList.clear()
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