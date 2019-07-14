package study.architecture.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import study.architecture.R

//TODO dataClass를 통해서 받아오는 새로운 데이터 폼 만들기
class CoinDataAdapter : RecyclerView.Adapter<CoinDataAdapter.Holder>() {
    private lateinit var list: List<Int>

    fun setList(list: List<Int>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, null)
        return Holder(view)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = list[position].toString()
        holder.price.text = list[position].toString()
        holder.last.text = list[position].toString()
        holder.money.text = list[position].toString()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val price: TextView = itemView.findViewById(R.id.price)
        val last: TextView = itemView.findViewById(R.id.last)
        val money: TextView = itemView.findViewById(R.id.money)

    }
}