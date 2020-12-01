package com.wybh.androidarchitecturestudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wybh.androidarchitecturestudy.databinding.CinemaItemBinding

class CinemaAdapter(private val itemClickListener: (link: String) -> Unit) :
    RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder>() {
    private val itemList: ArrayList<CinemaItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        val binding = DataBindingUtil.inflate<CinemaItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.cinema_item,
            parent,
            false
        )
        return CinemaViewHolder(binding, itemClickListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun dataClearAndSetting(item: List<CinemaItem>) {
        itemList.clear()
        itemList.addAll(item)
        notifyDataSetChanged()
    }

    class CinemaViewHolder(
        private val binding: CinemaItemBinding,
        private val itemClickListener: (link: String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var link = ""

        init {
            itemView.setOnClickListener {
                itemClickListener(link)
            }
        }

        fun bind(cinemaItem: CinemaItem) {
            binding.run {
                item = cinemaItem
                executePendingBindings()
            }
            link = cinemaItem.link
        }
    }
}