package com.jay.architecturestudy.ui.kin

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Kin
import com.jay.architecturestudy.ui.WebViewActivity
import kotlinx.android.synthetic.main.list_item_kin.view.*

internal class KinAdapter : RecyclerView.Adapter<KinHolder>() {
    private val data = arrayListOf<Kin>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_kin, parent, false)
        return KinHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: KinHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(kins: List<Kin>) {
        data.clear()
        data.addAll(kins)
        notifyDataSetChanged()
    }

}

internal class KinHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(model: Kin) {
        with(itemView) {
            kin_title.text = HtmlCompat.fromHtml(model.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            kin_description.text =
                HtmlCompat.fromHtml(model.description, HtmlCompat.FROM_HTML_MODE_COMPACT)

            setOnClickListener {
                val context = view.context
                Intent(context, WebViewActivity::class.java).apply {
                    putExtra(WebViewActivity.EXTRA_URL, model.link)
                }.run {
                    context.startActivity(this)
                }
            }
        }
    }
}