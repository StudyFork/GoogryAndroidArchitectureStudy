package sample.nackun.com.studyfirst.ui.ticker

import android.databinding.Observable
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.ticker_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.nackun.com.studyfirst.BR
import sample.nackun.com.studyfirst.base.BaseFragment
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource
import sample.nackun.com.studyfirst.databinding.TickerFragmentBinding
import sample.nackun.com.studyfirst.network.UpbitApi

class TickerFragment : BaseFragment<TickerFragmentBinding>(
    R.layout.ticker_fragment
) {

    private val tickerAdapter = TickerAdapter()

    private lateinit var vm: TickerViewModel

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        initViewModel()
        setAdapterAndClick()
    }

    private fun initViewModel() {
        vm = TickerViewModel(
            Repository(
                RemoteDataSource(
                    Retrofit.Builder()
                        .baseUrl("https://api.upbit.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(UpbitApi::class.java)
                )
            )
        )

        binding.setVariable(BR.vm, vm)

        vm.errMsg.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (sender is ObservableField<*>) {
                    Toast.makeText(
                        context,
                        (sender.get() as Throwable).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        vm.selectedTextView.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (sender is ObservableField<*>) {
                    setMarketColor(sender.get() as TextView)
                }
            }
        })
    }

    private fun setAdapterAndClick() {
        tickerRecyclerView.adapter = tickerAdapter
        tv_market_krw.callOnClick()
    }

    private fun setMarketColor(view: View) {
        listOf<View>(
            tv_market_krw,
            tv_market_btc,
            tv_market_eth,
            tv_market_usdt
        ).filter { view != it }
            .map { (it as TextView).setTextColor(ContextCompat.getColor(it.context, R.color.grey)) }

        if (view is TextView) {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.indigo))
        }
    }
}