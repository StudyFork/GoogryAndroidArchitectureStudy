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

    private lateinit var fragmentView: View
    private var nameList:ArrayList<String> = ArrayList()
    private var tickerList:List<Ticker> = ArrayList()
    private lateinit var timerTask: Timer
    private lateinit var recyclerAdapter: RecyclerViewAdapter

    //Bundle로 받아서 해야하는지 그냥 newInstance에서 받은것을 변수로 써도 되는지

    companion object {
        @JvmStatic
        fun newInstance(coinList:ArrayList<String>) =
            CoinFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList("PARM", coinList)
                    Log.d("qwer",coinList.toString())
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nameList = it.getStringArrayList("PARM")!!
            Log.d("abcd",nameList.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentView = inflater.inflate(R.layout.fragment_coin, container, false)
        loadData()
        return fragmentView
    }

    private fun loadData(){
        val coinService: CoinService = RetrofitUtil.retrofit.create(CoinService::class.java)

        //약 30초마다 갱신
        timerTask = timer(period = 30000){
            Log.d("CoinFragment","타이머")
            Log.d("Coin",nameList.toString())
            val call: Call<List<Ticker>> = coinService.getTicker(nameList.joinToString())

            call.enqueue(object :Callback<List<Ticker>>{
                override fun onFailure(call: Call<List<Ticker>>, t: Throwable) {
                    Toast.makeText(context, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<Ticker>>, response: Response<List<Ticker>>) {
                    Log.d("CoinFragment",response.body().toString())
                    response.body()?.let {
                        Toast.makeText(context, "FragonResponse!", Toast.LENGTH_LONG).show()
                        tickerList = response.body()!!
                        setRecyclerView()
                        recyclerAdapter.notifyDataSetChanged()
                    } ?: Toast.makeText(context,"프래그먼트 문제가 발생했습니다!", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    private fun setRecyclerView(){
        recyclerAdapter = RecyclerViewAdapter(nameList, tickerList, context!!)
        fragmentView.recyclerView.setHasFixedSize(true)
        fragmentView.recyclerView.adapter = recyclerAdapter
    }

}
