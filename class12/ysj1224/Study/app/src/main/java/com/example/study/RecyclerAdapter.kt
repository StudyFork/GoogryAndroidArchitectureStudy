package com.example.study

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.study.data.model.NaverApiData
import com.example.study.databinding.RvItemBinding

class RecyclerAdapter :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    private var item = mutableListOf<NaverApiData.Item>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding:RvItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.rv_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(data = item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setItem(apiItem: List<NaverApiData.Item>) {
        item.clear()
        item.addAll(apiItem)
        notifyDataSetChanged()

    }

    class MyViewHolder(private val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: NaverApiData.Item) {
            binding.vm = data
        }
    }
}

