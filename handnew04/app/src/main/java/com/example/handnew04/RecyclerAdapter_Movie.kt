package com.example.handnew04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class RecyclerAdapter_Movie(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter_Movie.ViewHolder>() {
    private lateinit var movies: ArrayList<NaverMovie_Response>

    fun setItemList(movies : ArrayList<NaverMovie_Response>) {
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
        holder.tv_title?.text = item.title
        holder.tv_pubDate?.text = item.pubDate.toString()
        holder.tv_director?.text = item.director
        holder.tv_actors?.text = item.actor
        holder.rb_userRating?.rating = item.userRating.toFloat()

        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it, position)
        }
    }

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