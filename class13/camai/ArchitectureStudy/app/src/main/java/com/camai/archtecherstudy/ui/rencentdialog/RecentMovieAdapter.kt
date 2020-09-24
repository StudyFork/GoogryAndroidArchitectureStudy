package com.camai.archtecherstudy.ui.rencentdialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.camai.archtecherstudy.R
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName
import com.camai.archtecherstudy.databinding.RecentItemBinding


class RecentMovieAdapter(private val presenter: RecentMovieContract.Presenter) :

    RecyclerView.Adapter<RecentMovieAdapter.ViewHolder>() {

    private val recentlist = mutableListOf<RecentSearchName>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recent_item,
            parent,
            false
        )
    ).apply {
        itemView.setOnClickListener {
            binding.recentPresenter = presenter as RecentMoviePresenter?
        }
    }


    override fun getItemCount() = recentlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = recentlist[position]
        holder.apply {
            bind(item)

        }
    }

    //  Update Movie Name Data
    fun setClearAndAddList(recentList: List<RecentSearchName>) {
        //  adaper clear and data add
        with(recentlist) {
            clear()
            addAll(recentList)
        }
        //  recyclerview set Data Change
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RecentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(nameInfo: RecentSearchName) {
            binding.apply {
                recentItem = nameInfo
                executePendingBindings()
            }
        }
    }
}