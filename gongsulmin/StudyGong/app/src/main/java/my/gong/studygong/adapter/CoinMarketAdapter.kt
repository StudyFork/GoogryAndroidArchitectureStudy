package my.gong.studygong.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import my.gong.studygong.R
import my.gong.studygong.databinding.ItemCoinMarketBinding
import my.gong.studygong.viewmodel.CoinViewModel

class CoinMarketAdapter(
    val coinViewModel: CoinViewModel
) : RecyclerView.Adapter<CoinMarketAdapter.ViewHolder>() {

    private val coinMarketList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding = DataBindingUtil.inflate<ItemCoinMarketBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_coin_market,
            parent,
            false
        )
        return ViewHolder(viewDataBinding)
    }

    override fun getItemCount() = coinMarketList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.viewDataBinding.coinViewModel = coinViewModel
        viewHolder.viewDataBinding.curreny = coinMarketList[position]
    }

    fun refreshData(coinMarketList: List<String>) {
        this.coinMarketList.run {
            clear()
            addAll(coinMarketList)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val viewDataBinding: ItemCoinMarketBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(viewDataBinding.root)
}