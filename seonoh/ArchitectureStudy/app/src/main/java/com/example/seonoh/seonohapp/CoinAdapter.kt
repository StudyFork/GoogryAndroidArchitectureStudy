package com.example.seonoh.seonohapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel

class CoinAdapter(data: ArrayList<CurrentPriceInfoModel>) : RecyclerView.Adapter<CoinItemViewHolder>(){

    private val mData = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {

        val mView = LayoutInflater.from(parent.context).inflate(R.layout.coin_item,parent,false)
        return CoinItemViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        holder.bind(mData,position)
    }
}

