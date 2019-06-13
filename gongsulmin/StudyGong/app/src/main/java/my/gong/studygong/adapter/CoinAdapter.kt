package my.gong.studygong.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import my.gong.studygong.R
import my.gong.studygong.data.model.Ticker
import my.gong.studygong.databinding.ItemTickerBinding

class CoinAdapter
    : androidx.recyclerview.widget.RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    private val coinList: MutableList<Ticker> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding = DataBindingUtil.inflate<ItemTickerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_ticker,
            parent,
            false
        )
        return ViewHolder(viewDataBinding)
    }

    override fun getItemCount() = coinList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.ticker = coinList[position]
    }

    fun refreshData(coinList: List<Ticker>) {
        this.coinList.run {
            clear()
            addAll(coinList)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val viewDataBinding: ItemTickerBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(viewDataBinding.root)
}