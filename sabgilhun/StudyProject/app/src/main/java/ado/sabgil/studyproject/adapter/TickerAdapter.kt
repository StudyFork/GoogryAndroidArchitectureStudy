package ado.sabgil.studyproject.adapter

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.data.model.Ticker
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class TickerAdapter : RecyclerView.Adapter<TickerViewHolder>() {

    private val items: MutableList<Ticker> by lazy { mutableListOf<Ticker>() }

    fun updateItem(items: List<Ticker>) {

        this.items.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    private fun getItem(position: Int) = this.items[position]


    override fun getItemCount() = this.items.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TickerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ticker, parent, false)
        )


    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.binding?.item = getItem(position)
    }

}