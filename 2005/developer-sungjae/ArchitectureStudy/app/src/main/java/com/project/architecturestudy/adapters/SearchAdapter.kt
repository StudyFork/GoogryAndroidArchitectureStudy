package com.project.architecturestudy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.architecturestudy.R
import com.project.architecturestudy.models.MovieData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list.view.*

class SearchAdapter(var searchData: ArrayList<MovieData.Items>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchingResultHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchingResultHolder -> {
                when (searchData[position].image.isEmpty()) {
                    true -> holder.ivMovie.setImageResource(R.drawable.ic_launcher_foreground)
                    false ->
                        Picasso.get().load(searchData[position].image)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .centerCrop()
                            .fit()
                            .into(holder.ivMovie)
                }

                holder.title.text = searchData[position].title
                holder.subTitle.text = searchData[position].subtitle
                holder.pubDate.text = searchData[position].pubDate
                holder.actors.text = searchData[position].actor
            }
        }
    }


    override fun getItemCount(): Int = searchData.count()


    inner class SearchingResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view = itemView
        var ivMovie = itemView.iv_movie
        var title = itemView.tv_title
        var subTitle = itemView.tv_subTitle
        var pubDate = itemView.tv_pubDate
        var actors = itemView.tv_actors
    }
}