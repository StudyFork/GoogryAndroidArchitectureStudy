package com.example.myproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R

class TitleAdapter(
    private val onListItemSelectedInterface: OnListItemSelectedInterface
) :
    RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    private val titleList = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.title_list, parent,
            false
        )
        val result = TitleViewHolder(view)

        view.setOnClickListener {
            val title = titleList[result.absoluteAdapterPosition]
            onListItemSelectedInterface.onItemSelected(title)
        }

        return result
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {

        holder.bind(titleList[position])
    }

    override fun getItemCount() = titleList.size

    fun setTitleList(titleList: ArrayList<String>) {
        with(this.titleList) {
            clear()
            addAll(titleList)
        }
        notifyDataSetChanged()
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)

        fun bind(title: String) {
            tvTitle?.text = title
        }

    }
}
