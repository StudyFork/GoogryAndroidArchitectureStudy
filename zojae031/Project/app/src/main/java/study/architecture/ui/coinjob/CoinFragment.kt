package study.architecture.ui.coinjob

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import study.architecture.Injection
import study.architecture.R
import study.architecture.data.local.LocalDataSourceImpl
import study.architecture.data.remote.RemoteDataSourceImpl
import study.architecture.data.repository.RepositoryImpl
import study.architecture.databinding.FragmentCoinBinding


@SuppressLint("ValidFragment", "WrongConstant")
class CoinFragment : Fragment() {
    private val coinDataAdapter = CoinDataAdapter()
    private lateinit var coinViewModel: CoinViewModel

    private lateinit var binding: FragmentCoinBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
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
            Injection.getRepository(context!!), coinDataAdapter
        ).also {
            coinViewModel = it
            binding.viewModel = it
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