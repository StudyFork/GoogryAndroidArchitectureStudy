package dev.daeyeon.gaasproject.ui.ticker.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.base.BaseDialogFragment
import dev.daeyeon.gaasproject.databinding.DialogFragmentTickerSearchBinding
import dev.daeyeon.gaasproject.ext.popContent
import dev.daeyeon.gaasproject.ui.ticker.TickerViewModel
import dev.daeyeon.gaasproject.util.Event
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TickerSearchDialogFragment : BaseDialogFragment<DialogFragmentTickerSearchBinding>(
    R.layout.dialog_fragment_ticker_search
) {
    private val tickerViewModel: TickerViewModel by sharedViewModel(
        from = {
            (parentFragment as? ViewModelStoreOwner) ?: this
        }
    )

    private val searchViewModel: TickerSearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            viewModel = tickerViewModel
            searchViewModel = this@TickerSearchDialogFragment.searchViewModel
            lifecycleOwner = this@TickerSearchDialogFragment.viewLifecycleOwner
        }

        subscribeCompleteEvent()
    }

    /**
     * 완료 이벤트 구독
     */
    private fun subscribeCompleteEvent() {
        searchViewModel.completeEvent.observe(this, Observer<Event<Unit>> { event ->
            event.popContent {
                tickerViewModel.loadUpbitTicker()
                this@TickerSearchDialogFragment.dismiss()
            }
        })
    }

    companion object {
        fun newInstance() = TickerSearchDialogFragment()
    }
}