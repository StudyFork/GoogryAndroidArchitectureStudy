package com.architecturestudy.market

import android.os.Bundle
import androidx.lifecycle.Observer
import com.architecturestudy.BR
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.base.BaseRecyclerView
import com.architecturestudy.common.MarketTypes
import com.architecturestudy.databinding.FragmentUpbitContentsBinding
import com.architecturestudy.databinding.RvUpbitItemBinding
import com.architecturestudy.util.RxEventBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.viewmodel.ext.android.viewModel

class UpbitContentsFragment : BaseFragment<FragmentUpbitContentsBinding, UpbitViewModel>(
    R.layout.fragment_upbit_contents
) {

    override val viewModel by viewModel<UpbitViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel
        initRecyclerView()
        showContents()
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
