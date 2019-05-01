package com.namjackson.archstudy.view

import android.arch.lifecycle.MutableLiveData
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.namjackson.archstudy.R
import com.namjackson.archstudy.data.repository.UpbitRepository
import com.namjackson.archstudy.databinding.FragmentCoinListBinding
import com.namjackson.archstudy.network.UpbitService
import com.namjackson.archstudy.view.adapter.CoinListAdapter
import java.util.*





private const val BASE_CURRENCY = "BASE_CURRENCY"

class CoinListFragment : Fragment() {

    private var adapter: CoinListAdapter? = null
    private var baseCurrency: String? = null
    private var markets: String? = null

    private lateinit var binding: FragmentCoinListBinding

    private lateinit var mTimer:Timer;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            baseCurrency = it.getString(BASE_CURRENCY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coin_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= CoinListAdapter();
        initLayout()
        initMarket()
    }


    fun initLayout(){
        binding.recyclerView.let {
            it.adapter=this.adapter
        }
    }

    fun initMarket(){
        UpbitRepository.getInstance(UpbitService.upbitApi).getMarketAll(
            baseCurrency?:"",
            success = {
                markets = it

                mTimer = Timer()
                mTimer.schedule(object : TimerTask() {
                    override fun run() {
                        getTicker(it)
                    }
                }, 0, (60 * 1000))

            },
            fail = {  Log.d("test", "eror : $it")  }
        )

    }

    fun getTicker(markets:String){
        UpbitRepository.getInstance(UpbitService.upbitApi).getTickers(
            markets,
            success = {adapter?.setList(it)},
            fail = {Log.d("test", "eror : $it")  }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mTimer.cancel();
    }

    companion object {
        fun newInstance(baseCurrency: String) =
            CoinListFragment().apply {
                arguments = Bundle().apply {
                    putString(BASE_CURRENCY, baseCurrency)
                }
            }
    }

}
