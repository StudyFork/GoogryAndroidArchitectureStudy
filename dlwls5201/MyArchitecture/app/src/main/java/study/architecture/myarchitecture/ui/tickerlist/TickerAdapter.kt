package study.architecture.myarchitecture.ui.tickerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.databinding.ItemTickerBinding
import study.architecture.myarchitecture.ui.model.TickerItem
import study.architecture.myarchitecture.util.setTradeDiffColor

class TickerAdapter(
    private val clickEvent: (ticker: TickerItem) -> Unit
) : RecyclerView.Adapter<TickerAdapter.TickerViewHolder>() {

    private val tickers = mutableListOf<TickerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {

        val holder = TickerViewHolder(parent)

        holder.itemView.setOnClickListener {
            clickEvent.invoke(tickers[holder.adapterPosition])
        }

        return holder
    }

    override fun getItemCount() = tickers.size

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.onBindView(tickers[position])
    }

    fun getCopyItems(): MutableList<TickerItem> {
        val copyItems = mutableListOf<TickerItem>()
        copyItems.addAll(tickers)
        return copyItems
    }

    fun setItems(newTickers: MutableList<TickerItem>) {
        tickers.clear()
        tickers.addAll(newTickers)
        notifyDataSetChanged()
    }

    /**
     * ViewHolder
     */
    class TickerViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemTickerBinding>(R.layout.item_ticker, parent) {

        fun onBindView(ticker: TickerItem) {

            with(binding) {
                tvMarket.text = ticker.coinName
                tvTradePrice.text = ticker.last
                tvSignedChangeRate.setTradeDiffColor(ticker.signedChangeRate)
                tvSignedChangeRate.text = ticker.tradeDiff
                tvAccTradePrice24h.text = ticker.tradeAmount

                executePendingBindings()
            }
        }
    }

    abstract class BaseViewHolder<B : ViewDataBinding>(
        @LayoutRes layoutRes: Int,
        parent: ViewGroup?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent?.context)
            .inflate(layoutRes, parent, false)
    ) {

        val binding: B = DataBindingUtil.bind(itemView)!!
    }

}