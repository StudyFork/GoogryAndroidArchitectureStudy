package com.hhi.myapplication.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hhi.myapplication.R
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.databinding.MainRecyclerItemBinding

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    private val movieList: ArrayList<MovieData.MovieItem> = ArrayList()

    fun setMovieList(list: ArrayList<MovieData.MovieItem>) {
        this.movieList.clear()
        this.movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MainRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    class ViewHolder(private val binding: MainRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieData.MovieItem) {
            Glide
                .with(binding.root).load(data.image)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.itemImgImage)

            binding.itemTextDirector.text =
                if (data.director.isEmpty()) "" else "감독 : " + data.director
            binding.itemTextActors.text = if (data.actor.isEmpty()) "" else "출연 : " + data.actor
            binding.itemTextTitle.text = data.title.replace("<b>", "").replace("</b>", "")
        }
    }
}