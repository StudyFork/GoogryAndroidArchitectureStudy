package kr.schoolsharing.coinhelper.tasks

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.base.BaseFragment
import kr.schoolsharing.coinhelper.data.Repository
import kr.schoolsharing.coinhelper.databinding.FragmentCoinrecyclerBinding

class UpbitFragment : BaseFragment<FragmentCoinrecyclerBinding>(R.layout.fragment_coinrecycler) {

    private lateinit var upbitViewModel: UpbitViewModel

    private val rVAdapter = UpbitRVAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding.rvCoin) {
            adapter = rVAdapter
            layoutManager = LinearLayoutManager(context!!)
            setHasFixedSize(true)
        }

        //TODO : MARKET_NAME enum으로 빼자
        val marketName = arguments?.get("MARKET_NAME").toString()
        upbitViewModel = UpbitViewModel(Repository)
        upbitViewModel.loadUpbitMarket(marketName)
        binding.viewModel = upbitViewModel
    }

    companion object {
        fun newInstance(marketName: String): UpbitFragment {
            val fragment = UpbitFragment()
            val bundle = Bundle()

            bundle.putString("MARKET_NAME", marketName)
            fragment.arguments = bundle
            return fragment
        }
    }

}