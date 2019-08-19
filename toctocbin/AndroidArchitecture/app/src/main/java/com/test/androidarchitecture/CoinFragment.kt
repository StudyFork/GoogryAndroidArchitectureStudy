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
import com.test.androidarchitecture.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_coin.*


class CoinFragment : Fragment() {

    private var retrofitService: RetrofitService? = null
    private var marketSearch: String = ""
    private var adapter: CoinAdapter? = null

    companion object {

        fun getInstance(marketSearch: String): CoinFragment {
            val args = Bundle()
            args.putString("marketSearch", marketSearch)
            val fragment = CoinFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        marketSearch = arguments?.getString("marketSearch")!!
        adapter = CoinAdapter()
        coin_recyclerView.adapter = this.adapter
        marketSearch?.let { loadCoinData(it) }
    }


    @SuppressLint("CheckResult")
    private fun loadCoinData(marketSearch: String) {
        retrofitService = RetrofitClient().getClient().create(RetrofitService::class.java)
        retrofitService?.loadCoinData(marketSearch)?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(this::coinResponse, this::coinError)
    }

    private fun coinResponse(coinList: List<Coin>) {
        adapter?.addItem(coinList)
        adapter?.notifyDataSetChanged()
    }

    private fun coinError(error: Throwable) {
        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
    }

}
