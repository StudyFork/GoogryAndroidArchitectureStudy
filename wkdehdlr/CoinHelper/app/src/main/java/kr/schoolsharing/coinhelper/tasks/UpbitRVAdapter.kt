package kr.schoolsharing.coinhelper.tasks

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.model.UpbitItem
import kr.schoolsharing.coinhelper.util.UpDown

class UpbitRVAdapter : RecyclerView.Adapter<UpbitRVAdapter.Holder>() {

    private val itemList: MutableList<UpbitItem> = ArrayList()

    override fun getItemCount(): Int = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(parent)

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(itemList[position])


    fun setTickerList(tickerList: List<UpbitItem>) {
        itemList.clear()
        itemList.addAll(tickerList)
        notifyDataSetChanged()
    }

    inner class Holder(parent: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_rv_item, parent, false)) {

        private val name = itemView.name
        private val current = itemView.current
        private val signedChangeRate = itemView.signedChangeRate
        private val volume = itemView.volume

        fun bind(upbitItem: UpbitItem) {
            name.text = upbitItem.name
            current.text = upbitItem.current

            when (upbitItem.change) {
                UpDown.RISE.toString() -> signedChangeRate.setTextColor(Color.BLUE)
                else -> signedChangeRate.setTextColor(Color.RED)
            }

            signedChangeRate.text = upbitItem.signedChangeRate
            volume.text = upbitItem.volume
        }
    }
}