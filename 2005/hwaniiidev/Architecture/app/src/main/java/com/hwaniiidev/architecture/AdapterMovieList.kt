package com.hwaniiidev.architecture

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hwaniiidev.architecture.model.Item

class AdapterMovieList(val context: Context, val movieList: List<Item>?) :
    RecyclerView.Adapter<AdapterMovieList.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie_list, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieList!!.get(position).link))
            context.startActivity(intent)
        }
        Glide.with(context)
            .load(movieList!!.get(position).image)
            .into(holder.imgMovie)
        holder.txtTile.text = Html.fromHtml(movieList!!.get(position).title)
        holder.txtSubTile.text = movieList!!.get(position).subtitle
        holder.txtPubDate.text = movieList!!.get(position).pubDate
        holder.txtDirector.text = movieList!!.get(position).director
        holder.txtActors.text = movieList!!.get(position).actor
        holder.txtRating.text = movieList!!.get(position).userRating
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgMovie = itemView.findViewById<ImageView>(R.id.item_movie_list_image)
        val txtTile = itemView.findViewById<TextView>(R.id.item_movie_list_title)
        val txtSubTile = itemView.findViewById<TextView>(R.id.item_movie_list_subtitle)
        val txtPubDate = itemView.findViewById<TextView>(R.id.item_movie_list_pub_date)
        val txtDirector = itemView.findViewById<TextView>(R.id.item_movie_list_director)
        val txtActors = itemView.findViewById<TextView>(R.id.item_movie_list_actors)
        val txtRating = itemView.findViewById<TextView>(R.id.item_movie_list_rating)
    }
}