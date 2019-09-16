package study.architecture.ui.coinjob

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.architecture.data.entity.ProcessingTicker
import study.architecture.databinding.ListItemBinding

/**
 * RecyclerView에 아이템을 뿌려주는 Adpater
 */
class CoinDataAdapter : RecyclerView.Adapter<CoinDataAdapter.Holder>(),
    CoinAdapterContract.View,
    CoinAdapterContract.Model {

    private val lists: MutableList<ProcessingTicker> = mutableListOf()
    private lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
        binding.executePendingBindings()
    }

    override fun notifyDataChange() {
        notifyDataSetChanged()
    }

    override fun updateList(list: List<ProcessingTicker>) {
        lists.addAll(list)
    }

    override fun clearList() {
        lists.clear()
    }

    inner class Holder(itemView: ListItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val name: TextView = itemView.name
        private val tradePrice: TextView = itemView.tradePrice
        private val changeRate: TextView = itemView.changeRate
        private val accTradePrice24h: TextView = itemView.accTradePrice

        fun bind(position: Int) {
            name.text = lists[position].market
            tradePrice.text = lists[position].tradePrice
            changeRate.setTextColor(lists[position].color)
            changeRate.text = lists[position].changeRate
            accTradePrice24h.text = lists[position].accTradePrice24h
        }

    }
}