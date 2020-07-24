package com.world.tree.architecturestudy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.world.tree.architecturestudy.R
import com.world.tree.architecturestudy.databinding.ItemMovieBinding
import com.world.tree.architecturestudy.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val movies: ArrayList<Movie.Item> = ArrayList()
    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setOnclickItemListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun addData(data: List<Movie.Item>) {
        movies.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData() {
        movies.clear();
    }

    interface OnItemClickListener {
        fun goToLink(link: String)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = DataBindingUtil.bind<ItemMovieBinding>(itemView)!!
        fun bind(data: Movie.Item) {
            binding.movie = data
            if (::onItemClickListener.isInitialized) binding.onItemClickListener =
                onItemClickListener
            binding.executePendingBindings()
        }
    }
}