package dev.daeyeon.gaasproject.ui.ticker.marketchoice

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.base.BaseDialogFragment
import dev.daeyeon.gaasproject.databinding.DialogFragmentMarketChoiceBinding
import dev.daeyeon.gaasproject.ext.popContent
import dev.daeyeon.gaasproject.ui.ticker.TickerFragment
import dev.daeyeon.gaasproject.util.Event
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MarketChoiceDialogFragment : BaseDialogFragment<DialogFragmentMarketChoiceBinding>(
    R.layout.dialog_fragment_market_choice
) {
    private lateinit var oldMarket: String
    private lateinit var markets: String

    private lateinit var chooseListener: (chooseMarket: String) -> Unit

    private val marketAdapter by lazy { setUpMarketAdapter() }

    private val choiceViewModel: MarketChoiceViewModel by viewModel { parametersOf(markets) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        chooseListener = {
            (parentFragment as? TickerFragment)?.refreshTickerByChooseMarket(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        oldMarket = arguments?.getString(EXTRA_OLD_MARKET) ?: ""
        markets = arguments?.getString(EXTRA_MARKETS) ?: ""
    }

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
            oldMarket = oldMarket,
            onMarketSelectedListener = { choiceViewModel.chooseMarket = it }
        )

    /**
     * 완료 이벤트 구독
     */
    private fun subscribeCompleteEvent() {
        choiceViewModel.completeEvent.observe(this, Observer<Event<Unit>> { event ->
            event.popContent {
                chooseListener(choiceViewModel.chooseMarket)
                this@MarketChoiceDialogFragment.dismiss()
            }
        })
    }

    companion object {
        private const val EXTRA_OLD_MARKET = "oldMarket"
        private const val EXTRA_MARKETS = "markets"

        fun newInstance(
            oldMarket: String,
            markets: String
        ) =
            MarketChoiceDialogFragment().apply {
                arguments = bundleOf(
                    EXTRA_OLD_MARKET to oldMarket,
                    EXTRA_MARKETS to markets
                )
            }
    }
}
