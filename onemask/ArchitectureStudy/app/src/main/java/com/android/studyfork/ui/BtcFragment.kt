package com.android.studyfork.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.studyfork.R
import com.android.studyfork.repository.UpbitApi
import com.android.studyfork.repository.UpbitService
import com.android.studyfork.ui.adpater.CoinItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_krw.*
import timber.log.Timber

fun newBtcFragment(arrayList : ArrayList<String>) : Fragment{
    return BtcFragment().apply {
        arguments = Bundle(1).apply {
            putStringArrayList("market",arrayList)
        }
    }
}

class BtcFragment : Fragment() {
    private var btcList : ArrayList<String> = arrayListOf()
    lateinit var upbitService: UpbitApi

    private lateinit var coinItemAdapter: CoinItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle =arguments
        bundle?.run {
            btcList = this.getStringArrayList("market")
        }
        setupApiService()
        getTicker(makePrameter())
    }
    private fun setupApiService() {
        upbitService = UpbitService.getInstance().upbitApi
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun makePrameter() : String{
        var Parameter = ""
        for (item in btcList.iterator()) {
            Parameter += "$item,"
        }
        Parameter=Parameter.substring(0,Parameter.length-1)
        Timber.d("Parameter $Parameter")
        return Parameter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_krw, container, false)
    }

    @SuppressLint("CheckResult")
    private fun getTicker(makrets : String){
        upbitService.getTikers(makrets)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("getTicker success")
                coinItemAdapter.prependData(it)
            },{
                Timber.e("${it.printStackTrace()}")
            })
    }

    private fun setRecyclerView() {
        coinItemAdapter = CoinItemAdapter()
        recyclerview.apply {
            adapter = coinItemAdapter
            addItemDecoration(DividerItemDecoration(context,1))
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
        }
    }


}
