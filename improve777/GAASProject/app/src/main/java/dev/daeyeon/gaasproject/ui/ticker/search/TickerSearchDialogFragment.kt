package dev.daeyeon.gaasproject.ui.ticker.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.Observer
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.base.BaseDialogFragment
import dev.daeyeon.gaasproject.databinding.DialogFragmentTickerSearchBinding
import dev.daeyeon.gaasproject.ui.ticker.TickerFragment
import dev.daeyeon.gaasproject.ui.ticker.TickerViewModel

class TickerSearchDialogFragment : BaseDialogFragment<DialogFragmentTickerSearchBinding>(
    R.layout.dialog_fragment_ticker_search
) {
    private val tickerViewModel by createViewModelLazy(
        viewModelClass = TickerViewModel::class,
        storeProducer = { parentFragment!!.viewModelStore },
        factoryProducer = { TickerFragment.getTickerViewModelFactory() }
    )

    private val searchViewModel by createViewModelLazy(
        viewModelClass = TickerSearchViewModel::class,
        storeProducer = { viewModelStore }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            viewModel = tickerViewModel
            searchViewModel = this@TickerSearchDialogFragment.searchViewModel
            lifecycleOwner = this@TickerSearchDialogFragment.viewLifecycleOwner
        }

        subscribeCompleteEvent()
    }

    private fun subscribeCompleteEvent() {
        searchViewModel.completeEvent.observe(this, Observer {
            tickerViewModel.loadUpbitTicker()
            this@TickerSearchDialogFragment.dismiss()
        })
    }

    companion object {
        fun newInstance() = TickerSearchDialogFragment()
    }
}