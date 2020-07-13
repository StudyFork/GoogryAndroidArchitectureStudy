package com.example.architecture.activity.search.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chul.data.model.MovieModel
import com.example.architecture.R
import com.example.architecture.databinding.ItemMovieBinding

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val movieList = mutableListOf<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        val viewHolder = MovieViewHolder(binding)

        binding.onItemClick = OnClickListener { view ->
            openWebPage(view.context, movieList[viewHolder.layoutPosition].link)
        }

        return viewHolder
    }

    private fun openWebPage(context: Context, link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return movieList.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movieList[position]
        holder.onBind(movie)
    }

    fun addNewItems(movieList: List<MovieModel>) {
        if (this.movieList.isNotEmpty()) {
            this.movieList.clear()
        }

        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }
}

class MovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(movie: MovieModel) {
        binding.movie = movie
        binding.executePendingBindings()
    }
}
