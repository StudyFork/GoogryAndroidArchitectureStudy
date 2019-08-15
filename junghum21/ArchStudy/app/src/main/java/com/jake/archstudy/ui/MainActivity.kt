package com.jake.archstudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jake.archstudy.R
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.databinding.ActivityMainBinding
import com.jake.archstudy.network.response.MarketResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val repository = UpbitRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getMarketAll()
    }

    private fun getMarketAll() {
        repository.getMarketAll()
            .enqueue(object : Callback<List<MarketResponse>?> {
                override fun onFailure(call: Call<List<MarketResponse>?>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<MarketResponse>?>,
                    response: Response<List<MarketResponse>?>
                ) {

                }
            })
    }

}