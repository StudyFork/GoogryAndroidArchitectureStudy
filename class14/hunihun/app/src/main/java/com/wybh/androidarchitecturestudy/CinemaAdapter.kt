package com.wybh.androidarchitecturestudy

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CinemaAdapter(private var itemList: ArrayList<CinemaItem>): RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cinema_item, parent, false)
        return CinemaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
    
    fun setList(list: ArrayList<CinemaItem>) {
        itemList = list
    }

    inner class CinemaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivThumnail = itemView.findViewById<ImageView>(R.id.iv_thumnail)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        private val tvPubData = itemView.findViewById<TextView>(R.id.tv_pubData)
        private val tvRating = itemView.findViewById<TextView>(R.id.tv_userRating)
        private val llItem = itemView.findViewById<LinearLayout>(R.id.ll_item)

        fun bind(item: CinemaItem) {

            Glide.with(itemView.context)
                .load(item.thumnail)
                .into(ivThumnail)

            tvTitle.text = "제목 : " + item.title
            tvPubData.text = "출시 : " + item.pubDate
            tvRating.text = "평점 : " + item.userRating

            llItem.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                itemView.context.startActivity(intent)
            }
        }
    }
}