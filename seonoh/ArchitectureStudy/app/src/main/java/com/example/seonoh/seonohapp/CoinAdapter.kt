package com.example.seonoh.seonohapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.UseCoinModel

class CoinAdapter : RecyclerView.Adapter<CoinItemViewHolder>() {

    private val mData: ArrayList<UseCoinModel> by lazy { ArrayList<UseCoinModel>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {

        val mView = LayoutInflater.from(parent.context).inflate(R.layout.coin_item, parent, false)
        return CoinItemViewHolder(mView)
    }

    override fun getItemCount(): Int = mData!!.size

    fun addCoinData(data: ArrayList<UseCoinModel>) {
        mData!!.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        holder.bind(mData!![position])
    }
}

