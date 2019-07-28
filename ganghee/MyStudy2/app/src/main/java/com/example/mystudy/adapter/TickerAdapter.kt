package com.example.mystudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.R
import com.example.mystudy.data.FormatTickers

class TickerAdapter(private var dataList: List<FormatTickers>) :
    RecyclerView.Adapter<UpbitHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UpbitHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_list, parent, false))

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: UpbitHolder, position: Int) {
        holder.bind(dataList[position])

    }
}
