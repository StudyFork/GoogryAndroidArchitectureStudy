package ado.sabgil.studyproject.adapter

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.data.model.Ticker
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class TickerAdapter : RecyclerView.Adapter<TickerViewHolder>() {

    private var items: MutableList<Ticker>? = null

    fun updateItem(items: List<Ticker>) {

        if (this.items == null) {
            this.items = mutableListOf()
        }

        this.items!!.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    private fun getItem(position: Int): Ticker {
        return this.items!![position]
    }

    override fun getItemCount(): Int {
        return this.items?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        return TickerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ticker, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.binding?.item = getItem(position)
    }

}