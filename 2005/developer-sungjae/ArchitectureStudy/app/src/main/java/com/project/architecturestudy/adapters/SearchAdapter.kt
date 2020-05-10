package com.project.architecturestudy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.architecturestudy.R
import com.project.architecturestudy.components.parseHTMLTag
import com.project.architecturestudy.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class SearchAdapter : RecyclerView.Adapter<SearchingResultHolder>() {

    private val searchList: MutableList<Movie.Items> = mutableListOf()
    var onClick: ((Movie.Items) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchingResultHolder {
        return SearchingResultHolder(parent).apply {
            itemView.setOnClickListener {
                val item = searchList[adapterPosition]
                onClick?.invoke(item)
            }
        }
    }

    override fun onBindViewHolder(holder: SearchingResultHolder, position: Int) {
        holder.bind(searchList[position])
    }

    override fun getItemCount(): Int = searchList.count()

    fun resetData(search: ArrayList<Movie.Items>) {
        searchList.clear()
        searchList.addAll(search)
        notifyDataSetChanged()
    }
}

class SearchingResultHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
) {
    fun bind(search: Movie.Items) {
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