package com.architecture.study.view.coin

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.architecture.study.R
import com.architecture.study.data.repository.CoinRepositoryImpl
import com.architecture.study.databinding.FragmentCoinlistBinding
import com.architecture.study.util.Injection
import com.architecture.study.view.coin.adapter.CoinListAdapter
import com.architecture.study.viewmodel.TickerViewModel

class CoinListFragment : Fragment(), CoinListAdapter.CoinItemRecyclerViewClickListener {

    private val tickerViewModel by lazy {
        TickerViewModel(
            CoinRepositoryImpl.getInstance(Injection.provideCoinRemoteDataSource()),
            tabList.map { getString(it) }
        )
    }

    private val coinListAdapter by lazy {
        CoinListAdapter(this)
    }

    private lateinit var binding: FragmentCoinlistBinding

    private lateinit var monetaryUnitNameList: List<String>

    private val tabList = listOf(
        R.string.monetary_unit_1,
        R.string.monetary_unit_2,
        R.string.monetary_unit_3,
        R.string.monetary_unit_4
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_coinlist,
            container,
            false
        )
        return binding.root
    }

    @Suppress("UNCHECKED_CAST")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /* 받아온 argument - Coin name */
        arguments?.getStringArrayList(MONETARY_UNIT_NAME_LIST)?.let {
            monetaryUnitNameList = it
        }
        tickerViewModel.getTickerList(monetaryUnitNameList)

        binding.run {
            tickerVM = tickerViewModel
            recyclerViewCoinList.run {
                adapter = coinListAdapter
            }
        }

        tickerViewModel.run {
            exceptionMessage.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    (sender as? ObservableField<String>)?.get()?.let {
                        showMessage(it)
                    }
                }
            })
        }
    }

    override fun onItemClicked(position: Int) {
        //click event
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(monetaryUnitNameList: List<String>?) = CoinListFragment().apply {
            arguments = Bundle().apply {
                monetaryUnitNameList?.let {
                    putStringArrayList(MONETARY_UNIT_NAME_LIST, ArrayList(monetaryUnitNameList))
                }
            }
        }

        private const val MONETARY_UNIT_NAME_LIST = "MONETARY_UNIT_NAME_LIST"
    }
}