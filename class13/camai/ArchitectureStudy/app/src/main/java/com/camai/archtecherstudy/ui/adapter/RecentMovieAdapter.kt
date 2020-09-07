package com.camai.archtecherstudy.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.camai.archtecherstudy.R
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName


class RecentMovieAdapter(
    val recentlist: List<RecentSearchName>,
    val itemClick: (String) -> Unit
) :
    RecyclerView.Adapter<RecentMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_item, parent, false)
        return ViewHolder(view).click { position ->
            val name: String = recentlist[position].movieName.toString()
            Log.d("Click", name)
            itemClick(name)
        }
    }

    fun <T : RecyclerView.ViewHolder> T.click(event: (position: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition)
        }
        return this
    }

    override fun getItemCount() = recentlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recentlist[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.txt_name)

        fun bind(nameInfo: RecentSearchName) {
            name?.text = nameInfo.movieName
        }
    }
}