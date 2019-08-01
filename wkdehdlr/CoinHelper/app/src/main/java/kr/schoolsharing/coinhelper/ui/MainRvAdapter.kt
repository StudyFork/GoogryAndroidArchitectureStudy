package kr.schoolsharing.coinhelper.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.model.UpbitItem
import java.util.*

class MainRvAdapter :
    RecyclerView.Adapter<MainRvAdapter.Holder>() {

    private val itemList: MutableList<UpbitItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_rv_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position])
    }

    fun setTickerList(tickerList: MutableList<UpbitItem>) {
        itemList.clear()
        itemList.addAll(tickerList)
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        fun bind(upbitItem: UpbitItem) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val current = itemView.findViewById<TextView>(R.id.current)
            val signedChangeRate = itemView.findViewById<TextView>(R.id.signedChangeRate)
            val volume = itemView.findViewById<TextView>(R.id.volume)

            name.text = upbitItem.name
            current.text = upbitItem.current

            when (upbitItem.change) {
                "RISE" -> signedChangeRate.setTextColor(Color.BLUE)
                else -> signedChangeRate.setTextColor(Color.RED)
            }

            signedChangeRate.text = upbitItem.signedChangeRate
            volume.text = upbitItem.volume
        }
    }

}