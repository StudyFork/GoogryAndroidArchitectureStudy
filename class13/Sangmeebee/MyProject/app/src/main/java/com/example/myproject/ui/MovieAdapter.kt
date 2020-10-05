package com.example.myproject.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.data.model.Items
import com.example.myproject.databinding.MovieItemBinding
import com.example.myproject.ui.MovieAdapter.ViewHolder


class MovieAdapter(private val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    private val movieArrayList = arrayListOf<Items>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<MovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_item,
            parent,
            false
        )
        val result = ViewHolder(binding)
        binding.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(movieArrayList[result.bindingAdapterPosition].link)
            context.startActivity(intent)
        }
        return result
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieArrayList[position])
    }

    override fun getItemCount() = movieArrayList.size

    fun clearItems() {
        movieArrayList.clear()
        notifyDataSetChanged()
    }

    fun clearAndAddItems(items: ArrayList<Items>) {
        movieArrayList.clear()
        movieArrayList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items) {
            binding.item = item
        }
    }
}
