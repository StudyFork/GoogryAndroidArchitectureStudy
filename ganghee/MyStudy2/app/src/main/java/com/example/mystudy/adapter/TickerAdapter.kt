package com.example.mystudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.R
import com.example.mystudy.data.FormatTickers

class TickerAdapter : RecyclerView.Adapter<UpbitHolder>() {

    private val tickerList = mutableListOf<FormatTickers>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UpbitHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_list, parent, false))

    override fun getItemCount(): Int = tickerList.size

    override fun onBindViewHolder(holder: UpbitHolder, position: Int) {
        holder.bind(tickerList[position])
    }

    fun setData(setDataList: List<FormatTickers>){
        this.tickerList.clear()
        this.tickerList.addAll(setDataList)
        notifyDataSetChanged()
    }
}
