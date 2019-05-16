package ado.sabgil.studyproject.adapter

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.data.model.Ticker
import android.view.LayoutInflater
import android.view.ViewGroup


class SearchedCoinTickerAdapter : BaseRecyclerViewAdapter<Ticker, SearchedCoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchedCoinViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_searched_ticker, parent, false)
    )

    override fun onBindViewHolder(holder: SearchedCoinViewHolder, position: Int) {
        holder.binding.item = items[position]
    }

}