package study.architecture.coinjob

import android.os.Bundle
import android.view.View
import study.architecture.BaseFragment
import study.architecture.Injection
import study.architecture.R
import study.architecture.databinding.FragmentCoinBinding


class CoinFragment : BaseFragment<FragmentCoinBinding>(R.layout.fragment_coin) {

    private lateinit var coinViewModel: CoinViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoinViewModel(
            arguments!!.getSerializable("idx") as FragIndex,
            Injection.getRepository(activity!!.applicationContext)
        ).also { coinViewModel = it }

        with(binding) {
            viewModel = coinViewModel
            recyclerView.adapter = CoinDataAdapter()
        }

    }

    override fun onPause() {
        coinViewModel.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        coinViewModel.onResume()
    }


    enum class FragIndex {
        KRW, BTC, ETH, USDT
    }

}