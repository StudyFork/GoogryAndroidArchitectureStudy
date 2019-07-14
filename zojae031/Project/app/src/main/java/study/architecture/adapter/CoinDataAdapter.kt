package study.architecture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.architecture.R

class CoinDataAdapter(private val list : List<Int>) : RecyclerView.Adapter<CoinDataAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,null)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        //TODO Model을 통해 아이템을 가져온다음 갯수 반환
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val last: TextView = itemView.findViewById(R.id.last)
        private val money: TextView = itemView.findViewById(R.id.money)

    }
}