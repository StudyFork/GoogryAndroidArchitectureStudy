package kr.schoolsharing.coinhelper.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.data.Repository
import kr.schoolsharing.coinhelper.databinding.FragmentCoinrecyclerBinding

class UpbitFragment : Fragment() {

    private lateinit var upbitViewModel: UpbitViewModel
    private lateinit var binding: FragmentCoinrecyclerBinding

    private val rVAdapter = UpbitRVAdapter()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_coinrecycler,
            container,
            false
        )
        return binding.root
    }
    

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