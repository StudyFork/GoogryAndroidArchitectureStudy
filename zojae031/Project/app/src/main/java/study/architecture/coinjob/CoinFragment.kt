package study.architecture.coinjob

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import study.architecture.Injection
import study.architecture.R
import study.architecture.databinding.FragmentCoinBinding


class CoinFragment : Fragment() {

    private lateinit var coinViewModel: CoinViewModel

    private lateinit var binding: FragmentCoinBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_coin,
            container,
            false
        )
        return binding.root
    }

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