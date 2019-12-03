package com.example.androidarchitecture.ui.kin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.R
import com.example.androidarchitecture.models.ResponseKin
import com.example.androidarchitecture.ui.WebviewActivity
import kotlinx.android.synthetic.main.item_blog.view.*

class kinAdapter(val items:List<ResponseKin>
) : RecyclerView.Adapter<kinAdapter.kinHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kinHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kin, parent, false)
        return kinHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: kinHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class kinHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ResponseKin) {
            with(view) {
                blog_title.text = item.title
                blog_description.text = item.description

                setOnClickListener() {
                    Intent(context, WebviewActivity::class.java).apply {
                        putExtra("link", item.link)
                    }.run { context.startActivity(this) }
                }
            }
        }

    }

}