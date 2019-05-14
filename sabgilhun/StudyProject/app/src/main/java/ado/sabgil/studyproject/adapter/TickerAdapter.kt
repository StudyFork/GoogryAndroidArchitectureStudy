package ado.sabgil.studyproject.adapter

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.data.model.Ticker
import android.view.LayoutInflater
import android.view.ViewGroup


class TickerAdapter : BaseRecyclerViewAdapter<Ticker, TickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TickerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_ticker, parent, false)
    )

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.binding.item = items[position]
    }

}