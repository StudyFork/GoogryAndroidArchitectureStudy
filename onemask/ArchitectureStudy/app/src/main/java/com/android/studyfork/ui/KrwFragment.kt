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
import com.android.studyfork.ext.inflate
import com.android.studyfork.repository.UpbitApi
import com.android.studyfork.repository.UpbitService
import com.android.studyfork.ui.adpater.CoinItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_krw.*
import timber.log.Timber

fun newKrwFragment(arrayList : ArrayList<String>) : Fragment{
    return KrwFragment().apply {
        arguments = Bundle(1).apply {
                putStringArrayList("market",arrayList)
            }
        }
}
class KrwFragment : Fragment() {
    private var krwList : ArrayList<String> = arrayListOf()
    lateinit var upbitService: UpbitApi

    private lateinit var coinItemAdapter: CoinItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle =arguments
        bundle?.run {
            krwList = this.getStringArrayList("market")
        }
        setupApiService()
        getTicker(makePrameter())

    }

    private fun setupApiService() {
        upbitService = UpbitService.getInstance().upbitApi
    }

    private fun makePrameter() : String{
        var krwParameter = ""
        for (item in krwList.iterator()) {
            krwParameter += "$item,"
        }
        krwParameter=krwParameter.substring(0,krwParameter.length-1)
        Timber.d("krwParameter $krwParameter")
        return krwParameter
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_krw)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
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


