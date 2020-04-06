package com.example.myapplication.ui

import android.annotation.TargetApi
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.data.local.MovieEntity
import com.example.myapplication.databinding.ItemMovieBinding

class SearchMovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movieInfoArrayList: MutableList<MovieEntity> = arrayListOf()
    private lateinit var onClickListener: (MovieEntity) -> Unit

    private lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        val holder = MovieViewHolder(binding)

        holder.itemView.setOnClickListener {
            onClickListener(movieInfoArrayList[holder.adapterPosition])
        }
        return holder
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = holder as MovieViewHolder
        val item = movieInfoArrayList[position]
        movieViewHolder.bind(item)
    }

    fun setOnclickListener(onClickListener: (MovieEntity) -> Unit) {
        this.onClickListener = onClickListener
    }

    override fun getItemCount(): Int {
        return movieInfoArrayList.size
    }

    fun addItems(items: List<MovieEntity>?) {
        items as ArrayList<MovieEntity>
        movieInfoArrayList.clear()
        movieInfoArrayList.addAll(items)
        notifyDataSetChanged()
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieEntity) {
            binding.setVariable(BR.movie, data)
            binding.executePendingBindings()
        }
    }
}