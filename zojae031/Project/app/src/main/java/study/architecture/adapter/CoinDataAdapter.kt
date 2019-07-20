package study.architecture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import study.architecture.R
import study.architecture.vo.Ticker

/**
 * RecyclerView에 아이템을 뿌려주는 Adpater
 */
class CoinDataAdapter : RecyclerView.Adapter<CoinDataAdapter.Holder>() {

    var lists: List<Ticker> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, null)
        view.dataWrapper.minimumWidth =
            view.context.applicationContext.resources.displayMetrics.widthPixels // 현재 Device 사이즈를 구하여 최소 width로 설정해준다.
        return Holder(view)
    }

    override fun getItemCount(): Int = lists.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = lists[position].market.substringAfter('-')
        val tradePrice = lists[position].tradePrice.toInt()
        if (tradePrice > 0) { //소수점이 아니라면 Int 형으로
            holder.tradePrice.text = String.format("%,d", tradePrice)
        } else {//소수점으로 내려간다면 double 형으로
            holder.tradePrice.text = String.format("%,f", lists[position].tradePrice)
        }

        holder.changeRate.text = String.format("%.2f%%", lists[position].changeRate * 100)
        holder.accTradePrice24h.text = String.format("%,d", lists[position].accTradePrice24h.toInt())
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val tradePrice: TextView = itemView.findViewById(R.id.trade_price)
        val changeRate: TextView = itemView.findViewById(R.id.change_rate)
        val accTradePrice24h: TextView = itemView.findViewById(R.id.acc_trade_price)
    }
}