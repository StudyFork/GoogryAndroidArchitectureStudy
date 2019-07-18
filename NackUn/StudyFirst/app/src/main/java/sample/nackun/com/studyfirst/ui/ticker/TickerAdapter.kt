package sample.nackun.com.studyfirst.ui.ticker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import sample.nackun.com.studyfirst.BR
import sample.nackun.com.studyfirst.R

class TickerAdapter<B : ViewDataBinding> : RecyclerView.Adapter<TickerAdapter<B>.TickerViewHolder<B>>() {

    private val items = mutableListOf<Map<String, String>>()

    fun setItems(tickers: List<Map<String, String>>) {
        items.clear()
        items.addAll(tickers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TickerViewHolder<B>(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(tickerViewHolder: TickerViewHolder<B>, position: Int) =
        items[position].let(tickerViewHolder::bind)

    inner class TickerViewHolder<B : ViewDataBinding>(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.ticker_item, parent, false)
    ) {
        val binding: B = DataBindingUtil.bind(itemView)!!

        fun bind(item: Map<String, String>) {
            binding.run {
                setVariable(BR.tickerItem, item)
            }
        }
    }
}
