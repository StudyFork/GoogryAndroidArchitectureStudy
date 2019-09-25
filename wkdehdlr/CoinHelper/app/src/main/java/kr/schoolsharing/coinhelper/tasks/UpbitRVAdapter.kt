package kr.schoolsharing.coinhelper.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.schoolsharing.coinhelper.databinding.MainRvItemBinding
import kr.schoolsharing.coinhelper.model.UpbitItem

class UpbitRVAdapter : RecyclerView.Adapter<UpbitRVAdapter.Holder>() {

    private val itemList: MutableList<UpbitItem> = ArrayList()

    override fun getItemCount(): Int = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(
            MainRvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
                false
            )
        )


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position])
    }

    fun setTickerList(tickerList: List<UpbitItem>?) {
        if (tickerList != null) {
            itemList.clear()
            itemList.addAll(tickerList)
            notifyDataSetChanged()
        }
    }

    class Holder(private val binding: MainRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(upbitItem: UpbitItem) {
            with(binding) {
                item = upbitItem
                executePendingBindings()
            }
        }
    }
}