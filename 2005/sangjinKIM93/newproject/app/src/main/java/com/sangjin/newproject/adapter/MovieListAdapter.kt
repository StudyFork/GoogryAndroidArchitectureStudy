package com.sangjin.newproject.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sangjin.newproject.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter (private val movieList: ArrayList<Movie>, private val context: Context) : RecyclerView.Adapter<MovieListViewHolder>(){

    var onItemClickListener : ((Int)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        item.setOnClickListener{
            var position: Long = item.tag as Long
            onItemClickListener?.invoke(position.toInt())
        }

        return MovieListViewHolder(item)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.containerView.movieTitleTV.text = movieList.get(position).title.htmlToString()

        Glide.with(context)
            .load(movieList.get(position).image)
            .placeholder(R.drawable.img_default)
            .centerCrop()
            .into(holder.containerView.movieImageIV)

        holder.containerView.tag = getItemId(position)
    }

    override fun getItemCount() = movieList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun String.htmlToString() : String{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            return Html.fromHtml(this).toString()
        }
    }

}