package com.example.architecturestudy.ui.coin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.Ticker

class CoinAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val coinList = mutableListOf<Ticker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = coinList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(coinList[position])

    fun setData(coinListData: List<Ticker>) {

        with(this.coinList){
            clear()
            addAll(coinListData)
        }
        notifyDataSetChanged()
    }
}