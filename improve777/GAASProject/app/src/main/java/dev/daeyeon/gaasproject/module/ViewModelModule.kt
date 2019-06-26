package dev.daeyeon.gaasproject.module

import dev.daeyeon.gaasproject.ui.ticker.TickerViewModel
import dev.daeyeon.gaasproject.ui.ticker.marketchoice.MarketChoiceViewModel
import dev.daeyeon.gaasproject.ui.ticker.search.TickerSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { TickerViewModel(get()) }

    viewModel { TickerSearchViewModel() }

    viewModel { (markets: String) -> MarketChoiceViewModel(markets) }
}
