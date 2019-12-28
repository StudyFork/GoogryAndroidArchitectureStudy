package com.example.handnew04

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


class MovieRecyclerAdapter :
    RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {
    private val movies: ArrayList<items> = ArrayList()

    fun setItemList(movies: ArrayList<items>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies.get(position)

        holder.iv_Image?.let { Glide.with(holder.itemView.context).load(item.image).into(it) }

        holder.tv_title?.text = getHtlmText(item.title)
        holder.tv_pubDate?.text = getHtlmText(item.pubDate)
        holder.tv_director?.text = getHtlmText(item.director)
        holder.tv_actors?.text = getHtlmText(item.actor)

        holder.rb_userRating?.rating = item.userRating.toFloat()

        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it, position)
        }
    }

    private fun getHtlmText(inputText: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(inputText, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(inputText).toString()
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