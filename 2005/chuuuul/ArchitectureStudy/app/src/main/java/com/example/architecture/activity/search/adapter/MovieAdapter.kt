package com.example.architecture.activity.search.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture.R

class MovieAdapter : RecyclerView.Adapter<MovieHolder>(), MovieAdapterContract.View {

    val movieAdapterPresenter = MovieAdapterPresenter(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val viewHolder = MovieHolder(view)

        view.setOnClickListener {
            showMovieWebPage(
                it.context,
                movieAdapterPresenter.getMovie(viewHolder.layoutPosition).link
            )
        }

        return viewHolder
    }

    override fun updateMovieList() {
        notifyDataSetChanged()
    }

    override fun showMovieWebPage(context: Context, link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return movieAdapterPresenter.getMovieCount()
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movieAdapterPresenter.getMovie(position)
        holder.onBind(movie)
    }
}


