package com.example.naversearchapistudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KinAdapter(val items: List<KinItems>) : RecyclerView.Adapter<KinAdapter.KinHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kin, parent, false)
        return KinHolder(view)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: KinHolder, position: Int) {
        holder.bind(items[position])
    }



    class KinHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val title = itemView.findViewById<TextView>(R.id.kin_title)
        val content = itemView.findViewById<TextView>(R.id.kin_contents)


        fun bind(KinData: KinItems) {
            title.text = KinData.title
            content.text = KinData.contents
        }
    }
}