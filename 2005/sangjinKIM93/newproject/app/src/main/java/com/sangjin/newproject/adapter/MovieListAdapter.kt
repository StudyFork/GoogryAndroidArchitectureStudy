package com.sangjin.newproject.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sangjin.newproject.R
import com.sangjin.newproject.data.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(clickListener : ((Int) -> Unit)) : RecyclerView.Adapter<MovieListViewHolder>() {

    private val movieList = ArrayList<Movie>()

    private val onItemClickListener = clickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {

        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieListViewHolder(item).apply {
            itemView.setOnClickListener {
                onItemClickListener(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = movieList.get(position)

        holder.containerView.movieTitleTV.text = movie.title.htmlToSpanned()

        Glide.with(holder.containerView.movieImageIV.context)
            .load(movie.image)
            .placeholder(R.drawable.img_default)
            .centerCrop()
            .into(holder.containerView.movieImageIV)

    }

    override fun getItemCount() = movieList.size


    fun addList(movieListFromActivity : List<Movie>){
        if(movieList.isNotEmpty()){
            movieList.clear()
        }

        movieList.addAll(movieListFromActivity)
        notifyDataSetChanged()
    }


    fun getMovieList(): ArrayList<Movie> = movieList


    private fun String.htmlToSpanned(): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(this)
        }
    }

}