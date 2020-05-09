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
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.hwaniiidev.architecture.model.Item


class AdapterMovieList :
    RecyclerView.Adapter<AdapterMovieList.MovieHolder>() {
    val movieList: ArrayList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)

        val holder = MovieHolder(view)
        //클릭시 브라우저로 영화 정보 조회
        holder.itemView.setOnClickListener { v ->
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse(movieList!!.get(holder.adapterPosition).link))
            view.context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val itemData = movieList!!.get(position)
        Glide.with(holder.itemView)
            .load(itemData.image)
            .into(holder.imgMovie)
        holder.txtTile.text = Html.fromHtml(itemData.title)
        holder.txtSubTile.text = itemData.subtitle
        holder.txtPubDate.text = itemData.pubDate
        holder.txtDirector.text = itemData.director
        holder.txtActors.text = itemData.actor
        holder.txtRating.text = itemData.userRating
    }

    fun addItem(items: List<Item>) {
        movieList.addAll(items)
        notifyDataSetChanged()
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