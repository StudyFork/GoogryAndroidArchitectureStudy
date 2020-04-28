package com.sangjin.newproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sangjin.newproject.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter (private val movieList: List<Movie>) : RecyclerView.Adapter<MovieListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)

        return MovieListViewHolder(item)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.containerView.movieTitleTV.text = deleteHTMLTag(movieList.get(position).title)
    }

    override fun getItemCount() = movieList.size


    private fun deleteHTMLTag(str: String): String{
        var strEdited = str.replace("<b>", "")
        strEdited = str.replace("</b>", "")

        return strEdited
    }

}