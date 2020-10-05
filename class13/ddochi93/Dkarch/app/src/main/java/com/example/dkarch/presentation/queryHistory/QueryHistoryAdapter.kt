package com.example.dkarch.presentation.queryHistory

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dkarch.R
import kotlinx.android.synthetic.main.item_query_history.view.*

class QueryHistoryAdapter(
    private val queryHistoryList: List<String>,
    private val queryItemClickedListener: (String) -> Unit
) :
    RecyclerView.Adapter<QueryHistoryAdapter.QueryViewHolder>() {

    class QueryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_query_history, parent, false)
    ) {
        private val queryTextView: TextView = itemView.query_text_view

        fun bind(query: String, queryItemClickedListener: (String) -> Unit) {
            queryTextView.text = query
            queryTextView.setOnClickListener {
                queryItemClickedListener(query)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        return QueryViewHolder(parent)
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {
        holder.bind(queryHistoryList[position], queryItemClickedListener)
    }

    override fun getItemCount(): Int {
        return queryHistoryList.size
    }

}
