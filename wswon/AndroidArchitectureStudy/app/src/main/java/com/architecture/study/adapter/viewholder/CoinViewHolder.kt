package com.architecture.study.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.architecture.study.R

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvCoinName = itemView.findViewById(R.id.coin_name_tv) as TextView
    var tvNowPrice = itemView.findViewById(R.id.now_price_tv) as TextView
    var tvCompareYesterday = itemView.findViewById(R.id.compare_yesterday_tv) as TextView
    var tvTransactionAmount = itemView.findViewById(R.id.transaction_amount_tv) as TextView
}