package com.project.architecturestudy.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.architecturestudy.R
import com.project.architecturestudy.components.parseHTMLTag
import com.project.architecturestudy.models.MovieData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class SearchAdapter : RecyclerView.Adapter<SearchingResultHolder>() {

    private var searchList: MutableList<MovieData.Items> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchingResultHolder {
        return SearchingResultHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchingResultHolder, position: Int) {
        holder.bindItem(searchList[position])
    }

    override fun getItemCount(): Int = searchList.count()

    fun resetData(searchData: ArrayList<MovieData.Items>) {
        searchList.clear()
        this.searchList = searchData
        notifyDataSetChanged()
    }
}

class SearchingResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItem(searchData: MovieData.Items) {
        when (searchData.image.isEmpty()) {
            true -> itemView.iv_movie.setImageResource(R.drawable.ic_launcher_foreground)
            false -> Picasso.get()
                .load(searchData.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .fit()
                .into(itemView.iv_movie)
        }
        itemView.tv_title.text = searchData.title.parseHTMLTag()
        itemView.tv_subTitle.text = searchData.subtitle.parseHTMLTag()
        itemView.tv_pubDate.text = searchData.pubDate.parseHTMLTag()
        itemView.tv_actors.text = searchData.actor.parseHTMLTag()
        itemView.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(searchData.link))
            itemView.context.startActivity(webIntent)
        }
    }
}