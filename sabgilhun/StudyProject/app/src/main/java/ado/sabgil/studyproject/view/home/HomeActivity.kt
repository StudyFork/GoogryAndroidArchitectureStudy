package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandler
import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandlerImpl
import ado.sabgil.studyproject.data.remote.upbit.request.UpbitTickerListRequest
import ado.sabgil.studyproject.databinding.ActivityMainBinding
import ado.sabgil.studyproject.enums.BaseCurrency
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var upbitApiHandler: UpbitApiHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rvTickerList.adapter = TickerAdapter()

        upbitApiHandler = UpbitApiHandlerImpl

        upbitApiHandler.getAllTickers(BaseCurrency.KRW,
            { result ->
                binding.it = result.map { Ticker.from(it) }.toMutableList()
            },
            {})
    }
}
