package com.example.hw2_project.recentSearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_project.R
import com.example.hw2_project.main.MainActivity
import kotlinx.android.synthetic.main.recent_movie_item.view.*

class RecentSearchRecyclerViewAdapter : RecyclerView.Adapter<RecentSearchRecyclerViewAdapter.ViewHolder>() {

    private val queryList = mutableListOf<String>()

    fun updateMovieList(list : List<String>?){
        list?.let {
            this.queryList.clear()
            this.queryList.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return queryList.size
    }

    override fun onBindViewHolder(
        holder: RecentSearchRecyclerViewAdapter.ViewHolder, position: Int) {
        val query = queryList[position]
        val listener = View.OnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            intent.putExtra("query", query)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            it.context.startActivity(intent)
        }

        holder.bind(query, listener)
    }

    inner class ViewHolder(recentMovieItemView: View) : RecyclerView.ViewHolder(recentMovieItemView){

        fun bind(query: String, listener: View.OnClickListener){
            itemView.recent_item_text_title.text = query
            itemView.recent_item_text_title.setOnClickListener(listener)
        }
    }

}