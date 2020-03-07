package com.example.architecturestudy.ui.kin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.KinItem
import com.example.architecturestudy.databinding.ItemKinBinding
import com.example.architecturestudy.ui.startWebView

class KinAdapter : RecyclerView.Adapter<KinAdapter.KinHolder>() {

    private val kinItem : MutableList<KinItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        val binding = DataBindingUtil.inflate<ItemKinBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_kin,
            parent,
            false
        )

        return KinHolder(binding).apply {
            itemView.setOnClickListener { v -> v.startWebView(kinItem[adapterPosition].link) }
        }
    }

    override fun getItemCount(): Int {
        return kinItem.size
    }

    override fun onBindViewHolder(holder: KinHolder, position: Int) {
        holder.bind(kinItem[position])
    }

    fun update(kinList : List<KinItem>) {
        this.kinItem.clear()
        this.kinItem.addAll(kinList)
        notifyDataSetChanged()
    }

    class KinHolder(private val binding : ItemKinBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: KinItem) {
            binding.kin = item
        }
    }
}