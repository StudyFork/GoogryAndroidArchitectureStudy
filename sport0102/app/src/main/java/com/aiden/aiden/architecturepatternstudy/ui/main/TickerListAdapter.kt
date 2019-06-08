package com.aiden.aiden.architecturepatternstudy.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aiden.aiden.architecturepatternstudy.R

class TickerListAdapter() : RecyclerView.Adapter<TickerListAdapter.ItemTickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTickerViewHolder {
        val retView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticker, parent, false)
        return ItemTickerViewHolder(retView)
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: ItemTickerViewHolder, position: Int) {

    }

    inner class ItemTickerViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
