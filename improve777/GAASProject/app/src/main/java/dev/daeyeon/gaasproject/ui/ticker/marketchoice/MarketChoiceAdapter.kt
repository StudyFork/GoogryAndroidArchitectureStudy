package dev.daeyeon.gaasproject.ui.ticker.marketchoice

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.daeyeon.gaasproject.databinding.DialogFragmentMarketChoiceItemBinding

class MarketChoiceAdapter(
    private val oldMarket: String,
    private val onMarketSelectedListener: ((market: String) -> Unit)
) : RecyclerView.Adapter<MarketChoiceAdapter.Companion.MarketViewMolder>() {

    private val list = mutableListOf<String>()

    private var lastSelectedPosition: Int = -1

    fun replaceAll(marketList: List<String>) {
        list.clear()
        list.addAll(marketList)
        lastSelectedPosition = list.indexOf(oldMarket)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewMolder {
        val binding = DialogFragmentMarketChoiceItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MarketViewMolder(binding).apply {
            this.binding.vClick.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    lastSelectedPosition = adapterPosition
                    onMarketSelectedListener(list[adapterPosition])
                    notifyDataSetChanged()
                    return@setOnTouchListener true
                }
                return@setOnTouchListener false
            }
        }
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: MarketViewMolder, position: Int) {
        holder.bind(
            list[position],
            lastSelectedPosition == position
        )
    }

    companion object {
        class MarketViewMolder(
            val binding: DialogFragmentMarketChoiceItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(market: String, isSelected: Boolean) {
                binding.market = market
                binding.selected = isSelected
            }
        }
    }
}
