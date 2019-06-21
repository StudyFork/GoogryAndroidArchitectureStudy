package ado.sabgil.studyproject.adapter

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.data.model.Ticker
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class TickerAdapter : RecyclerView.Adapter<TickerViewHolder>() {

    private val item: MutableList<Ticker> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TickerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_ticker, parent, false)
    )

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.binding.item = item[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = item.size

    fun update(item: List<Ticker>) {
        this.item.let {
            it.clear()
            it.addAll(item)
        }
    }
}