package com.nanamare.mac.sample.ui

import android.os.Bundle
import com.google.gson.Gson
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.api.DisposableManager
import com.nanamare.mac.sample.base.BaseActivity
import com.nanamare.mac.sample.databinding.ActivityMainBinding
import com.nanamare.mac.sample.ui.market.MarketListFragment
import com.nanamare.mac.sample.vm.MarketViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val marketVM: MarketViewModel by lazy { MarketViewModel() }

    private val disposableManager = DisposableManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(marketVM) {
            showLoadingDialog()
            onMarketClick()
            disposableManager.add(
                marketObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        hideLoadingDialog()
                        gotoFragment(it)
                    }, {
                        hideLoadingDialog()
                    })
            )
        }

    }

    private fun gotoFragment(it: LinkedHashMap<String, List<String>>) {
        val bundle = Bundle().apply {
            putString(KET_MARKET_LIST, Gson().toJson(it))
        }
        goToFragment(MarketListFragment::class.java, bundle)
    }

    override fun onDestroy() {
        marketVM.close()
        disposableManager.dispose()
        super.onDestroy()
    }

}
