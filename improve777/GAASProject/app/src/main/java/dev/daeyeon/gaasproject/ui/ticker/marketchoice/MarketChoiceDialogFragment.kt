package dev.daeyeon.gaasproject.ui.ticker.marketchoice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.Observer
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.base.BaseDialogFragment
import dev.daeyeon.gaasproject.databinding.DialogFragmentMarketChoiceBinding
import dev.daeyeon.gaasproject.ui.ticker.TickerFragment
import dev.daeyeon.gaasproject.ui.ticker.TickerViewModel

class MarketChoiceDialogFragment : BaseDialogFragment<DialogFragmentMarketChoiceBinding>(
    R.layout.dialog_fragment_market_choice
) {
    private val marketAdapter by lazy { setUpMarketAdapter() }

    private val tickerViewModel by createViewModelLazy(
        viewModelClass = TickerViewModel::class,
        storeProducer = { parentFragment!!.viewModelStore },
        factoryProducer = TickerFragment.Companion::getTickerViewModelFactory
    )

    private val choiceViewModel by createViewModelLazy(
        viewModelClass = MarketChoiceViewModel::class,
        storeProducer = { viewModelStore }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            choiceViewModel = this@MarketChoiceDialogFragment.choiceViewModel
            lifecycleOwner = this@MarketChoiceDialogFragment.viewLifecycleOwner
            rvMarket.adapter = marketAdapter
        }

        subscribeCompleteEvent()
    }

    private fun setUpMarketAdapter() =
        MarketChoiceAdapter(
            oldMarket = tickerViewModel.getBaseCurrency(),
            onMarketSelectedListener = initBaseMarketSelectedListener()
        ).apply {
            replaceAll(tickerViewModel.getCurrencyArray().toList())
        }

    private fun subscribeCompleteEvent() {
        choiceViewModel.completeEvent.observe(this, Observer {
            tickerViewModel.loadUpbitTicker()
            dismiss()
        })
    }

    companion object {
        fun newInstance() = MarketChoiceDialogFragment()
    }

    private fun initBaseMarketSelectedListener(): ((market: String) -> Unit) =
        {
            tickerViewModel.setBaseCurrency(it)
        }
}
