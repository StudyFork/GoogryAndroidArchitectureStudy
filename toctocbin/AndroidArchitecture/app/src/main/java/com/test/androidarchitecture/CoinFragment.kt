package com.test.androidarchitecture


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.test.androidarchitecture.adpter.CoinAdapter
import com.test.androidarchitecture.data.Coin
import com.test.androidarchitecture.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_coin.*


class CoinFragment : Fragment() {

    private var marketSearch: String = ""
    private val adapter by lazy { CoinAdapter() }
    private val retrofitService by lazy { RetrofitClient.getInstance().retrofitService }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        marketSearch = arguments?.getString("marketSearch")!!
        coin_recyclerView.adapter = this.adapter
        marketSearch.let { loadCoinData(it) }
    }


    @SuppressLint("CheckResult")
    private fun loadCoinData(marketSearch: String) {
        retrofitService.loadCoinData(marketSearch).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t: List<Coin> ->
                run {
                    adapter.addItem(t)
                }
            }, { t: Throwable -> Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show() })
    }

    companion object {

        fun getInstance(marketSearch: String): CoinFragment {
            val args = Bundle()
            args.putString("marketSearch", marketSearch)
            val fragment = CoinFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
