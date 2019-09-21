package study.architecture.ui.coinjob

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import study.architecture.data.entity.ProcessingTicker
import study.architecture.databinding.ItemTickerBinding

/**
 * RecyclerView에 아이템을 뿌려주는 Adpater
 */
class CoinDataAdapter : RecyclerView.Adapter<CoinDataAdapter.Holder>() {

    private val lists: MutableList<ProcessingTicker> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemTickerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    fun updateLists(list: List<ProcessingTicker>) {
        lists.clear()
        lists.addAll(list)
        notifyDataSetChanged()
    }


    inner class Holder(private val binding: ItemTickerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                pTicker = lists[position]
                executePendingBindings()
            }
        }

    }
}