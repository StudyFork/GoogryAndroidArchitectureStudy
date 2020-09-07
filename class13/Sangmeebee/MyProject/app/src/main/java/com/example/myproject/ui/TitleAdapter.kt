package com.example.myproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R

class TitleAdapter(val titleArrayList: ArrayList<String>) :
    RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.title_list, parent,
            false
        )
        return TitleViewHolder(view)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {

        holder.bind(titleArrayList[position])
    }

    override fun getItemCount() = titleArrayList.size

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)

        fun bind(title: String) {
            tvTitle?.text = title
        }

    }
}
