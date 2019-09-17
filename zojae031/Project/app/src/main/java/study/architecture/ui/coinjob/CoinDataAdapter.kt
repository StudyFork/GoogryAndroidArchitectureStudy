package study.architecture.ui.coinjob

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import study.architecture.data.entity.ProcessingTicker
import study.architecture.databinding.ListItemBinding

/**
 * RecyclerView에 아이템을 뿌려주는 Adpater
 */
class CoinDataAdapter : RecyclerView.Adapter<CoinDataAdapter.Holder>() {

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

    fun updateLists(list: List<ProcessingTicker>) {
        lists.clear()
        lists.addAll(list)
        notifyDataSetChanged()
    }


    inner class Holder(itemView: ListItemBinding) : RecyclerView.ViewHolder(itemView.root) {

        fun bind(position: Int) {
            binding.pTicker = lists[position]
        }

    }
}