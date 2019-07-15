package sample.nackun.com.studyfirst.ui.ticker

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import kotlinx.android.synthetic.main.ticker_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.nackun.com.studyfirst.BR
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.base.BaseFragment
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource
import sample.nackun.com.studyfirst.databinding.TickerFragmentBinding
import sample.nackun.com.studyfirst.network.UpbitApi

class TickerFragment : BaseFragment<TickerFragmentBinding>(
    R.layout.ticker_fragment
) {

    private val tickerAdapter = TickerAdapter()

    private lateinit var vm: TickerViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        setAdapter()
        setFirstTickers()
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
    }

    private fun setAdapter() {
        tickerRecyclerView.adapter = tickerAdapter
    }

    private fun setFirstTickers() {
        vm.selectedMarket.get()?.let {
            vm.showTickers(it)
        } ?: vm.errMsg.set(IllegalStateException("Selected Market is not exist"))
    }
}