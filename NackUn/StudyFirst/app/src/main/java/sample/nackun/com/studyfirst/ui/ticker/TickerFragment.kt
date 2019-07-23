package sample.nackun.com.studyfirst.ui.ticker

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.ticker_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.nackun.com.studyfirst.BR
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.base.BaseFragment
import sample.nackun.com.studyfirst.base.BaseRecyclerView
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource
import sample.nackun.com.studyfirst.databinding.TickerFragmentBinding
import sample.nackun.com.studyfirst.databinding.TickerItemBinding
import sample.nackun.com.studyfirst.network.UpbitApi

class TickerFragment : BaseFragment<TickerFragmentBinding>(
    R.layout.ticker_fragment
) {
    private val firstMarketName = "KRW"
    private val tickerAdapter =
        object :
            BaseRecyclerView.BaseAdapter<List<Map<String, String>>, TickerItemBinding>(
                R.layout.ticker_item,
                BR.tickerItem
            ) {}

    private lateinit var vm: TickerViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        setAdapter()
        setFirstTickers()
    }

    private fun initViewModel() {
        vm = ViewModelProviders.of(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    TickerViewModel(
                        Repository(
                            RemoteDataSource(
                                Retrofit.Builder()
                                    .baseUrl("https://api.upbit.com/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
                                    .create(UpbitApi::class.java)
                            )
                        )
                    ) as T
            }
        )[TickerViewModel::class.java]

        binding.setVariable(BR.vm, vm)

        val errMsgObserver = Observer<Throwable> {
            showToast(it.message)
        }

        vm.errMsg.observe(this, errMsgObserver)
    }

    private fun setAdapter() {
        tickerRecyclerView.adapter = tickerAdapter
    }

    private fun setFirstTickers() =
        vm.showTickers(firstMarketName)
}