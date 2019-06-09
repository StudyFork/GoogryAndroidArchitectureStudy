package my.gong.studygong.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_coin_market.view.*
import my.gong.studygong.R

class CoinMarketAdapter(
    val clickCoinMarketListener: (String) -> Unit
) : RecyclerView.Adapter<CoinMarketAdapter.ViewHolder>() {


    private val coinMarketList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_coin_market, parent, false))

    override fun getItemCount() = coinMarketList.size


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.coinMarket.text = coinMarketList[position]
        viewHolder.parentCoinMarket.setOnClickListener {
            clickCoinMarketListener.invoke(coinMarketList[position])
        }
    }

    fun refreshData(coinMarketList: List<String>) {
        this.coinMarketList.run {
            clear()
            addAll(coinMarketList)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coinMarket = view.txt_coin_market
        val parentCoinMarket = view.linear_coin_market
    }
}