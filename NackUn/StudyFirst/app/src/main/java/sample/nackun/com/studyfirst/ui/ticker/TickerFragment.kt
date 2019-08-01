package sample.nackun.com.studyfirst.ui.ticker

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.ticker_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sample.nackun.com.studyfirst.BR
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.base.BaseFragment
import sample.nackun.com.studyfirst.base.BaseRecyclerView
import sample.nackun.com.studyfirst.databinding.TickerFragmentBinding
import sample.nackun.com.studyfirst.databinding.TickerItemBinding

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

    private val vm: TickerViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        setAdapter()
        setFirstTickers()
    }

    private fun initViewModel() {
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