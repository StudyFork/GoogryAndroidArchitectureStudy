package com.example.architecture_project.feature.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_project.data.model.Movie
import com.example.architecture_project.databinding.ItemMovieBinding

class MovieAdapter(private val clickListener: MovieViewHolder.ItemClickListener) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movieData: ArrayList<Movie> = ArrayList()

    fun setMovieItemList(newMovieData: List<Movie>) {
        with(movieData) {
            clear()
            addAll(newMovieData)
        }
        notifyDataSetChanged()
    }

    fun getMovieLink(position: Int): String {
        return movieData[position].link
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val binding: ItemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
        return MovieViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int {
        return movieData.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieData[position])
    }


    class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val clickListener: ItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.root.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

        interface ItemClickListener {
            fun onItemClick(position: Int)
        }

        fun bind(data: Movie) {
            binding.movie = data
        }
    }
}