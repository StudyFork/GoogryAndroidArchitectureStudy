package kr.schoolsharing.coinhelper.tasks

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.base.BaseFragment
import kr.schoolsharing.coinhelper.data.Repository
import kr.schoolsharing.coinhelper.databinding.FragmentCoinrecyclerBinding
import kr.schoolsharing.coinhelper.util.Name

class UpbitFragment : BaseFragment<FragmentCoinrecyclerBinding>(R.layout.fragment_coinrecycler) {

    private val rVAdapter = UpbitRVAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding.rvCoin) {
            adapter = rVAdapter
            layoutManager = LinearLayoutManager(context!!)
            setHasFixedSize(true)
        }

        val marketName = arguments?.get(Name.MARKET_NAME.toString()).toString()
        binding.viewModel = UpbitViewModel(Repository).apply {
            loadUpbitMarket(marketName)
        }
    }

    companion object {
        fun newInstance(marketName: String): UpbitFragment {
            val fragment = UpbitFragment()
            val bundle = Bundle()

            bundle.putString(Name.MARKET_NAME.toString(), marketName)
            fragment.arguments = bundle
            return fragment
        }
    }

}