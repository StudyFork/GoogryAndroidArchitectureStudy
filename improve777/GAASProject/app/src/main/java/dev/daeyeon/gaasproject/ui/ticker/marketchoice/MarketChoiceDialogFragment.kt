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
import dev.daeyeon.gaasproject.util.Event

class MarketChoiceDialogFragment : BaseDialogFragment<DialogFragmentMarketChoiceBinding>(
    R.layout.dialog_fragment_market_choice
) {
    private val marketAdapter by lazy {
        MarketChoiceAdapter(
            oldMarket = tickerViewModel.baseMarket.value!!,
            onMarketSelectedListener = { choiceViewModel.selectedMarket = it }
        )
    }

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

        choiceViewModel.setMarketList(tickerViewModel.getMarkets())

        bind {
            choiceViewModel = this@MarketChoiceDialogFragment.choiceViewModel
            lifecycleOwner = this@MarketChoiceDialogFragment.viewLifecycleOwner
            rvMarket.adapter = marketAdapter
        }

        subscribeCompleteEvent()
    }

    /**
     * 완료 이벤트 구독
     */
    private fun subscribeCompleteEvent() {
        choiceViewModel.completeEvent.observe(this, Observer<Event<Unit>> { event ->
            event.getContentIfNotHandled()?.let {
                tickerViewModel.setBaseMarket(choiceViewModel.selectedMarket)
                tickerViewModel.loadUpbitTicker()
                this@MarketChoiceDialogFragment.dismiss()
            }
        })
    }

    companion object {
        fun newInstance() = MarketChoiceDialogFragment()
    }
}
