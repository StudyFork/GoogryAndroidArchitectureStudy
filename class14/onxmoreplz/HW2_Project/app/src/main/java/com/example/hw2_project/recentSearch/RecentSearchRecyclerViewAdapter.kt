package com.example.hw2_project.recentSearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_project.R
import com.example.hw2_project.databinding.RecentMovieItemBinding
import com.example.hw2_project.main.MainActivity

class RecentSearchRecyclerViewAdapter :
    RecyclerView.Adapter<RecentSearchRecyclerViewAdapter.ViewHolder>() {

    private val queryList = mutableListOf<String>()

    fun updateMovieList(list: List<String>?) {
        list?.run {
            queryList.clear()
            queryList.addAll(this)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = DataBindingUtil.inflate<RecentMovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recent_movie_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return queryList.size
    }

    override fun onBindViewHolder(
        holder: RecentSearchRecyclerViewAdapter.ViewHolder, position: Int
    ) {
        val query = queryList[position]
        val listener = View.OnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            intent.putExtra("query", query)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            it.context.startActivity(intent)
        }

        holder.bind(query, listener)
    }

    inner class ViewHolder(private val recentMovieItemBinding: RecentMovieItemBinding) :
        RecyclerView.ViewHolder(recentMovieItemBinding.root) {
        fun bind(query: String, listener: View.OnClickListener) {
            recentMovieItemBinding.recentItemTextTitle.text = query
            recentMovieItemBinding.recentItemTextTitle.setOnClickListener(listener)
            recentMovieItemBinding.executePendingBindings()
        }
    }

}