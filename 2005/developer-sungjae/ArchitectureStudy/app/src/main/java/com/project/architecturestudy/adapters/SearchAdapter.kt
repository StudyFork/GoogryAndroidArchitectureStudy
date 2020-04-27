package com.project.architecturestudy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.architecturestudy.R
import com.project.architecturestudy.models.MovieData

class SearchAdapter(var searchData: ArrayList<MovieData.Items>) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchingResultHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }


    override fun getItemCount(): Int {
        return 0
    }

    inner class SearchingResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view = itemView
    }
}