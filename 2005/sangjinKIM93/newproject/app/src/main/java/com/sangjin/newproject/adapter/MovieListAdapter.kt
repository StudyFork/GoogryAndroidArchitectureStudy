package com.sangjin.newproject.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sangjin.newproject.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(clickListener : ((Int) -> Unit)) : RecyclerView.Adapter<MovieListViewHolder>() {

    private val movieList = ArrayList<Movie>()
    private lateinit var context: Context

    private val onItemClickListener = clickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        context = parent.context

        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        val movieListViewHolder = MovieListViewHolder(item)

        item.setOnClickListener {
            var position = movieListViewHolder.adapterPosition
            onItemClickListener(position)
        }

        return movieListViewHolder
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = movieList.get(position)

        holder.containerView.movieTitleTV.text = movie.title.htmlToString()

        Glide.with(context)
            .load(movie.image)
            .placeholder(R.drawable.img_default)
            .centerCrop()
            .into(holder.containerView.movieImageIV)

        holder.containerView.tag = getItemId(position)
    }

    override fun getItemCount() = movieList.size


    fun addList(movieListFromActivity : ArrayList<Movie>){
        if(movieList.isNotEmpty()){
            movieList.clear()
        }

        movieList.addAll(movieListFromActivity)
        notifyDataSetChanged()
    }


    fun String.htmlToString(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(this).toString()
        }
    }

}