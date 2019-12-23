package com.example.handnew04

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class RecyclerAdapter_Movie(private val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter_Movie.ViewHolder>() {
    private var movies: ArrayList<items> = ArrayList()

    fun setItemList(movies: ArrayList<items>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies.get(position)

        holder.iv_Image?.let { Glide.with(context).load(item.image).into(it) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tv_title?.text = Html.fromHtml(item.title, Html.FROM_HTML_MODE_LEGACY)
            holder.tv_pubDate?.text = Html.fromHtml(item.pubDate, Html.FROM_HTML_MODE_LEGACY)
            holder.tv_director?.text = Html.fromHtml(item.director, Html.FROM_HTML_MODE_LEGACY)
            holder.tv_actors?.text = Html.fromHtml(item.actor, Html.FROM_HTML_MODE_LEGACY)
        } else {
            holder.tv_title?.text = Html.fromHtml(item.title)
            holder.tv_pubDate?.text = Html.fromHtml(item.pubDate)
            holder.tv_director?.text = Html.fromHtml(item.director)
            holder.tv_actors?.text = Html.fromHtml(item.actor)
        }

        holder.rb_userRating?.rating = item.userRating.toFloat()

        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it, position)
        }
    }

    fun getMovieLink(position: Int): String = movies.get(position).link


    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }


    private lateinit var itemClickListner: ItemClickListener


    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }


    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val iv_Image = itemView?.findViewById<ImageView>(R.id.iv_movieImage)
        val tv_title = itemView?.findViewById<TextView>(R.id.tv_movieTitle)
        val tv_pubDate = itemView?.findViewById<TextView>(R.id.tv_moviePubDate)
        val tv_director = itemView?.findViewById<TextView>(R.id.tv_movieDirector)
        val tv_actors = itemView?.findViewById<TextView>(R.id.tv_movieActors)
        val rb_userRating = itemView?.findViewById<RatingBar>(R.id.rb_userRating)

    }
}