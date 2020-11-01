package kr.dktsudgg.androidarchitecturestudy.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_info_layout.view.*
import kr.dktsudgg.androidarchitecturestudy.R
import kr.dktsudgg.androidarchitecturestudy.api.naver.data.MovieItem

class MovieListAdapter(val context: Context, var movieList: MutableList<MovieItem>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.movie_info_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var movieItem = movieList[position]

        holder.title.text = movieItem.title
        holder.subtitle.text = movieItem.subtitle
        holder.director.text = movieItem.director
        holder.actor.text = movieItem.actor
        holder.userRating.text = movieItem.userRating
        holder.image.text = movieItem.image
        holder.link.text = movieItem.link
        holder.pubDate.text = movieItem.pubDate
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val subtitle = itemView.subtitle
        val director = itemView.director
        val actor = itemView.actor
        val userRating = itemView.userRating
        val image = itemView.image
        val link = itemView.link
        val pubDate = itemView.pubDate
    }

}