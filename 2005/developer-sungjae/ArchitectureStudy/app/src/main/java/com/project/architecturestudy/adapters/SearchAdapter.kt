package com.project.architecturestudy.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.architecturestudy.R
import com.project.architecturestudy.models.MovieData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class SearchAdapter(var searchData: ArrayList<MovieData.Items>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchingResultHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchingResultHolder -> holder.bindItem(searchData[position])


        }
    }

    override fun getItemCount(): Int = searchData.count()

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
        itemView.tv_title.text = searchData.title
        itemView.tv_subTitle.text = searchData.subtitle
        itemView.tv_pubDate.text = searchData.pubDate
        itemView.tv_actors.text = searchData.actor
        itemView.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(searchData.link))
            itemView.context.startActivity(webIntent)
        }
    }
}