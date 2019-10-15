package com.test.androidarchitecture.ui.ticker


import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Observable
import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.TickerAdapter
import com.test.androidarchitecture.base.BaseFragment
import com.test.androidarchitecture.databinding.FragmentCoinBinding

class TickerFragment : BaseFragment<FragmentCoinBinding, TickerViewModel>(R.layout.fragment_coin){

    override val vm by lazy { TickerViewModel(arguments?.getString(MARKET_SEARCH) ?: "") }
    private val adapter by lazy { TickerAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.coinRecyclerView.adapter = this.adapter
        setObservableCallBack()
        vm.getTicker()
    }

    private fun setObservableCallBack(){
        vm.tickerList.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.tickerList.get()?.let { adapter.setItem(it) }
            }
        })
        vm.toastMessage.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(activity, vm.toastMessage.get(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {

        private const val MARKET_SEARCH: String = "marketSearch"

        fun getInstance(marketSearch: String): TickerFragment {
            val args = Bundle()
            args.putString(MARKET_SEARCH, marketSearch)
            val fragment = TickerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
