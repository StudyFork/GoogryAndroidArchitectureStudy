package com.architecture.study.view.coin

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.architecture.study.BR
import com.architecture.study.R
import com.architecture.study.base.BaseAdapter
import com.architecture.study.base.BaseFragment
import com.architecture.study.data.model.Ticker
import com.architecture.study.data.repository.CoinRepositoryImpl
import com.architecture.study.databinding.FragmentCoinlistBinding
import com.architecture.study.databinding.ItemTickerBinding
import com.architecture.study.util.Injection
import com.architecture.study.viewmodel.TickerViewModel

class CoinListFragment : BaseFragment<FragmentCoinlistBinding>(R.layout.fragment_coinlist) {

    private val tickerViewModel by lazy {
        TickerViewModel(
            CoinRepositoryImpl.getInstance(Injection.provideCoinRemoteDataSource()),
            tabList.map { getString(it) }
        )
    }

    private lateinit var monetaryUnitList: List<String>

    private val tabList = listOf(
        R.string.monetary_unit_1,
        R.string.monetary_unit_2,
        R.string.monetary_unit_3,
        R.string.monetary_unit_4
    )

    @Suppress("UNCHECKED_CAST")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.run {
            tickerVM = tickerViewModel
            recyclerViewCoinList.run {
                adapter = object :
                    BaseAdapter<Ticker, ItemTickerBinding>(R.layout.item_ticker, BR.ticker) {}
            }
        }

        tickerViewModel.run {
            exceptionMessage.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    (sender as? ObservableField<String>)?.get()?.let {
                        showMessage(it)
                    }
                }
            })
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun setMonetaryUnitList(monetaryUnitList: List<String>){
        this.monetaryUnitList = monetaryUnitList
        tickerViewModel.getTickerList(this.monetaryUnitList)
    }

    companion object {
        fun newInstance() = CoinListFragment()
    }
}