package com.example.myarchitecutrestudy.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myarchitecutrestudy.R
import com.example.myarchitecutrestudy.adapter.RecyclerViewAdapter
import com.example.myarchitecutrestudy.model.Ticker
import com.example.myarchitecutrestudy.network.CoinService
import com.example.myarchitecutrestudy.utils.RetrofitUtil
import kotlinx.android.synthetic.main.fragment_coin.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer


class CoinFragment : Fragment() {

    private val TAG : String = CoinFragment::class.java.simpleName
    private lateinit var fragmentView: View
    private var nameList:ArrayList<String> = ArrayList()
    private lateinit var timerTask: Timer
    private lateinit var recyclerAdapter: RecyclerViewAdapter

    companion object {
        @JvmStatic
        fun newInstance(coinList:ArrayList<String>) =
            CoinFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList("PARM", coinList)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nameList = it.getStringArrayList("PARM")?:ArrayList()
            Log.d(TAG, nameList.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentView = inflater.inflate(R.layout.fragment_coin, container, false)
        setRecyclerView()
        loadData()
        return fragmentView
    }

    private fun loadData(){
        val coinService: CoinService = RetrofitUtil.retrofit.create(CoinService::class.java)

        timerTask = timer(period = 60 * 1000){
            val call: Call<List<Ticker>> = coinService.getTicker(nameList.joinToString())

            call.enqueue(object :Callback<List<Ticker>>{
                override fun onFailure(call: Call<List<Ticker>>, t: Throwable) {
                    Toast.makeText(context, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<Ticker>>, response: Response<List<Ticker>>) {
                    Log.d(TAG, response.body().toString())
                    response.body()?.let {
                        Toast.makeText(context, "FragonResponse!", Toast.LENGTH_SHORT).show()
                        recyclerAdapter.setList(it as ArrayList<Ticker>)
                    } ?: Toast.makeText(context,"프래그먼트 문제가 발생했습니다!", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    private fun setRecyclerView(){
        recyclerAdapter = RecyclerViewAdapter(nameList,context!!)
        fragmentView.recyclerView.setHasFixedSize(true)
        fragmentView.recyclerView.adapter = recyclerAdapter
    }

}
