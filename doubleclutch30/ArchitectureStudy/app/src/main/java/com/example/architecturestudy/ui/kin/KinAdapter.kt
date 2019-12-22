package com.example.architecturestudy.ui.kin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.KinItems
import com.example.architecturestudy.ui.startWebView
import kotlinx.android.synthetic.main.item_kin.view.*

class KinAdapter : RecyclerView.Adapter<KinAdapter.KinHolder>() {

    private val kinItem : MutableList<KinItems> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kin, parent, false)
        return KinHolder(view).apply {
            itemView.setOnClickListener { v -> v.startWebView(kinItem[adapterPosition].link) }
        }
    }

    override fun getItemCount(): Int {
        return kinItem.size
    }

    override fun onBindViewHolder(holder: KinHolder, position: Int) {
        holder.bind(kinItem[position])
    }

    fun update(kinList : List<KinItems>) {
        this.kinItem.clear()
        this.kinItem.addAll(kinList)
        notifyDataSetChanged()
    }

    class KinHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(item: KinItems) {
            with(itemView) {
                kin_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                kin_contents.text = HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            }
        }
    }
}