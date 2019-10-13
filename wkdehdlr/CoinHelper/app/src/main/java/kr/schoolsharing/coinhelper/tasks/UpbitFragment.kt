package kr.schoolsharing.coinhelper.tasks

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.base.BaseFragment
import kr.schoolsharing.coinhelper.base.BaseRecyclerViewAdapter
import kr.schoolsharing.coinhelper.databinding.FragmentCoinrecyclerBinding
import kr.schoolsharing.coinhelper.databinding.MainRvItemBinding
import kr.schoolsharing.coinhelper.model.UpbitItem
import kr.schoolsharing.coinhelper.util.Market
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpbitFragment : BaseFragment<FragmentCoinrecyclerBinding>(R.layout.fragment_coinrecycler) {


    val viewModel by viewModel<UpbitViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding.rvCoin) {
            adapter = object : BaseRecyclerViewAdapter<UpbitItem, MainRvItemBinding>(
                R.layout.main_rv_item,
                BR.item
            ) {}

            setHasFixedSize(true)
        }

        val marketName = arguments?.get(Market.NAME.name).toString()
        viewModel.loadUpbitMarket(marketName)
//        binding.viewModel = UpbitViewModel(Repository).apply {
//            loadUpbitMarket(marketName)
//        }

//        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
//            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
//                UpbitViewModel(Repository) as T
//        }).get(UpbitViewModel::class.java)
//            .loadUpbitMarket(marketName)

    }

    companion object {
        fun newInstance(marketName: String): UpbitFragment {
            val fragment = UpbitFragment()
            val bundle = Bundle()

            bundle.putString(Market.NAME.name, marketName)
            fragment.arguments = bundle
            return fragment
        }
    }

}