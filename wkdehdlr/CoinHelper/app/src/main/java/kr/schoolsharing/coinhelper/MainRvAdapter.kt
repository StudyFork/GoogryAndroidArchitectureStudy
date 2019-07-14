package kr.schoolsharing.coinhelper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainRvAdapter(val context: Context, val itemList: ArrayList<UpbitItem>) :
    RecyclerView.Adapter<MainRvAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.main_rv_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(itemList[position], context)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        fun bind(upbitItem: UpbitItem, context: Context) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val current = itemView.findViewById<TextView>(R.id.current)
            val diff = itemView.findViewById<TextView>(R.id.diff)
            val volume = itemView.findViewById<TextView>(R.id.volume)

            name.text = upbitItem.name
            current.text = upbitItem.current
            diff.text = upbitItem.diff
            volume.text = upbitItem.volume
        }
    }

}