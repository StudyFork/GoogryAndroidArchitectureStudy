package kr.schoolsharing.coinhelper.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.schoolsharing.coinhelper.databinding.MainRvItemBinding
import kr.schoolsharing.coinhelper.model.UpbitItem

class UpbitRVAdapter : RecyclerView.Adapter<UpbitRVAdapter.Holder>() {

    private val itemList: MutableList<UpbitItem> = ArrayList()
    private lateinit var binding: MainRvItemBinding

    override fun getItemCount(): Int = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        binding = MainRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position])
        binding.executePendingBindings()
    }


    fun setTickerList(tickerList: List<UpbitItem>) {
        itemList.clear()
        itemList.addAll(tickerList)
        notifyDataSetChanged()
    }

    inner class Holder(itemView: MainRvItemBinding) : RecyclerView.ViewHolder(itemView.root) {

        fun bind(upbitItem: UpbitItem) {
            with(binding) {
                item = upbitItem
            }
        }
    }
}