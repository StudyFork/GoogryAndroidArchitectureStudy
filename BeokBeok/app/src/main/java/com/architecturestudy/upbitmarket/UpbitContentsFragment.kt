package com.architecturestudy.upbitmarket

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.architecturestudy.BR
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.base.BaseRecyclerView
import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.source.UpbitRepository
import com.architecturestudy.data.upbit.source.local.UpbitLocalDataSource
import com.architecturestudy.data.upbit.source.local.UpbitRoom
import com.architecturestudy.data.upbit.source.remote.UpbitRemoteDataSource
import com.architecturestudy.data.upbit.source.remote.UpbitRetrofit
import com.architecturestudy.databinding.FragmentUpbitContentsBinding
import com.architecturestudy.databinding.RvUpbitItemBinding
import com.architecturestudy.util.RxEventBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpbitContentsFragment : BaseFragment<FragmentUpbitContentsBinding>(
    R.layout.fragment_upbit_contents
) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
        initRecyclerView()
        showContents()
    }

    private fun initBinding() {
        binding.vm = ViewModelProviders.of(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    UpbitViewModel(
                        UpbitRepository(
                            UpbitLocalDataSource(UpbitRoom.getDao(context)),
                            UpbitRemoteDataSource(UpbitRetrofit.service)
                        )
                    ) as T
            }
        )[UpbitViewModel::class.java]
    }

    private fun initRecyclerView() {
        binding.rvCoinPrice.run {
            setHasFixedSize(true)
            adapter =
                object :
                    BaseRecyclerView.BaseAdapter<List<Map<String, String>>, RvUpbitItemBinding>(
                        R.layout.rv_upbit_item,
                        BR.marketList
                    ) {}
        }
    }

    private fun showContents() {
        binding.vm?.let {
            it.showMarketPrice(MarketTypes.values()[getPosition()].name)
            it.errMsg.observe(
                this,
                Observer { throwable ->
                    showToast(throwable.message)
                }
            )
            CompositeDisposable().add(
                RxEventBus.getEvents()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        @Suppress("UNCHECKED_CAST")
                        if (data is List<*>) {
                            val ticker = (data as? List<Map<String, String>>)
                                ?.asSequence()
                                ?.filter { map ->
                                    map["prefix"] == MarketTypes.values()[getPosition()].name
                                }
                                ?.toList()
                            it.marketPriceList.value = ticker
                        } else if (data is Throwable) {
                            it.errMsg.value = data
                        }
                    }, { throwable ->
                        showToast(throwable.message)
                    })
            )
        }
    }

    private fun getPosition(): Int = arguments?.getInt("marketType", 0) ?: 0
}
