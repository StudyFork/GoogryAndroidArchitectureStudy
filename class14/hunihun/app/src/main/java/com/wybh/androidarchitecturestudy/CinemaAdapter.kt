package com.wybh.androidarchitecturestudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wybh.androidarchitecturestudy.databinding.CinemaItemBinding

class CinemaAdapter(private val itemClickListener: (link: String) -> Unit) :
    RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder>() {
    private lateinit var binding: CinemaItemBinding
    private val itemList: ArrayList<CinemaItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.cinema_item,
            parent,
            false
        )
        return CinemaViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun addList(item: List<CinemaItem>) {
        itemList.addAll(item)
    }

    fun dataClear() {
        itemList.clear()
    }

    fun dataClearAndSetting(item: List<CinemaItem>) {
        itemList.clear()
        itemList.addAll(item)
        notifyDataSetChanged()
    }

    inner class CinemaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                itemClickListener(itemList[adapterPosition].link)
            }
        }

        fun bind(cinemaItem: CinemaItem) {
            binding.run {
                item = cinemaItem
                executePendingBindings()
            }
        }
    }
}