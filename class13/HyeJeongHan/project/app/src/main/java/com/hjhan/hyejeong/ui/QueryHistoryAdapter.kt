package com.hjhan.hyejeong.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hjhan.hyejeong.R

class QueryHistoryAdapter :
    RecyclerView.Adapter<QueryHistoryAdapter.QueryViewHolder>() {

    private val list = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_query, parent,
            false
        )

        return QueryViewHolder(view)
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {

        holder.bind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setMovieList(list: List<String>) {
        with(this.list) {
            clear()
            addAll(list)
        }

        notifyDataSetChanged()
    }

    class QueryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var query = itemView.findViewById(R.id.query_text_view) as TextView

        fun bind(data: String) {
            query.text = data
        }
    }
}