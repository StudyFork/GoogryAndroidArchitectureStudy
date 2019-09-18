package study.architecture.myarchitecture.ui.tickerlist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import study.architecture.myarchitecture.BR
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.base.BaseBindingViewHolder
import study.architecture.myarchitecture.base.BaseViewHolder
import study.architecture.myarchitecture.databinding.ItemTickerBinding
import study.architecture.myarchitecture.ui.model.TickerItem

class TickerAdapter(
    private val clickEvent: (ticker: TickerItem) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val tickers = mutableListOf<TickerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val holder = TickerBindingViewHolder(parent)

        holder.itemView.setOnClickListener {
            clickEvent.invoke(tickers[holder.adapterPosition])
        }

        return holder
    }

    override fun getItemCount() = tickers.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBindView(tickers[position])
    }

    fun setItems(newTickers: MutableList<TickerItem>) {
        tickers.clear()
        tickers.addAll(newTickers)
        notifyDataSetChanged()
    }

    /**
     * ViewHolder
     */
    class TickerBindingViewHolder(parent: ViewGroup) :
        BaseBindingViewHolder<ItemTickerBinding>(R.layout.item_ticker, parent, BR.tickerItem)
}