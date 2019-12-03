package com.example.androidarchitecture.ui.kin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.R
import com.example.androidarchitecture.models.ResponseKin
import kotlinx.android.synthetic.main.item_blog.view.*
import kotlinx.android.synthetic.main.item_image.view.*

class kinAdapter(val items:List<ResponseKin>,
                 val mContext: Context,
                 val mOnItemClickListener: OnItemClickListener): RecyclerView.Adapter<kinAdapter.kinHolder>() {


    interface OnItemClickListener {
        fun onItemClick(link: String)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kinHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kin, parent, false)
        return kinHolder(view)    }

    override fun getItemCount(): Int {
        return items.size    }

    override fun onBindViewHolder(holder: kinHolder, position: Int) {
        val item = items.get(position)

        holder.itemView.blog_title.text = item.title
        holder.itemView.blog_description.text = item.description

        holder.itemView.setOnClickListener() {
            mOnItemClickListener.onItemClick(item.link)
        }
    }







    inner class kinHolder(view: View) : RecyclerView.ViewHolder(view)

}