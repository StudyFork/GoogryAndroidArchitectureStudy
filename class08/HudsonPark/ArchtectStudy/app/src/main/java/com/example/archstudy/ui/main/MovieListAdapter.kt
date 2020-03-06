package com.example.archstudy.ui.main

import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.archstudy.BR
import com.example.archstudy.data.source.local.MovieData
import com.example.archstudy.R
import com.example.archstudy.databinding.ItemMovieBinding

class MovieListAdapter(@Nullable private var listener: ItemClickListener) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var list = mutableListOf<MovieData>()
    private lateinit var binding: ItemMovieBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )

        val holder = ViewHolder(binding)

        holder.itemView.setOnClickListener {
            listener.onItemClick(list[holder.adapterPosition].link)
        }
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position])

    fun setAllData(movieList: MutableList<MovieData>) {
        list.clear()
        list.addAll(movieList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MovieData) {

                Log.d("img", "image : ${data.image}, title : ${data.title}")
                with(binding) {
                    setVariable(BR.movieData, data)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(url: String)
    }

}