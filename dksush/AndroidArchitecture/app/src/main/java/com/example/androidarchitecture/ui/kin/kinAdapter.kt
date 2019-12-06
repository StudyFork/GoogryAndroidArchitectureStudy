package com.example.androidarchitecture.ui.kin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecture.R
import com.example.androidarchitecture.models.ResponseKin
import com.example.androidarchitecture.ui.WebviewActivity
import kotlinx.android.synthetic.main.item_blog.view.*

class KinAdapter (
    val context: Context
) : RecyclerView.Adapter<KinAdapter.KinHolder>() {

    private val data = arrayListOf<ResponseKin>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_kin, parent, false)
        return KinHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: KinHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(item: List<ResponseKin>) {
        data.clear()
        data.addAll(item)
        notifyDataSetChanged()

    }

    inner class KinHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var item: ResponseKin
        init {
            view.setOnClickListener(){
                Intent(context, WebviewActivity::class.java).apply {
                    putExtra("link", item.link)
                }.run { context.startActivity(this) }

            }
        }

        fun bind(item: ResponseKin) {
            this.item = item
            with(view) {
                blog_title.text = item.title
                blog_description.text = item.description
            }
        }

    }

}