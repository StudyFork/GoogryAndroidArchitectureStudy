package com.camai.archtecherstudy.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.camai.archtecherstudy.R
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.databinding.MovieItemBinding

class MovieSearchAdapter() :
    RecyclerView.Adapter<MovieSearchAdapter.ViewHolder>() {

    private val movieInfoArrayList = mutableListOf<Items>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
    )

    override fun getItemCount() = movieInfoArrayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movieInfoArrayList[position]
        holder.apply {
            bind(item)

        }
    }

    //  Update Movie List Data
    fun setClearAndAddList(movielist: ArrayList<Items>) {
        //  adaper clear and movielist data add
        with(movieInfoArrayList) {
            clear()
            addAll(movielist)
        }
        //  recyclerview set Data Change
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemInfo: Items) {
            binding.apply {
                movieItem = itemInfo
                executePendingBindings()
            }
        }
    }
}
