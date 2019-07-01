package my.gong.studygong.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import my.gong.studygong.R
import my.gong.studygong.adapter.CoinAdapter
import my.gong.studygong.databinding.ActivityMainBinding
import my.gong.studygong.viewmodel.CoinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinListActivity : AppCompatActivity() {

    private val coinMarketDialog = CoinMarketDialog()

    private val coinViewModel: CoinViewModel by viewModel()

    private lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this@CoinListActivity, R.layout.activity_main)
        viewDataBinding.coinViewModel = coinViewModel
        viewDataBinding.lifecycleOwner = this
        init()
    }

    override fun onStart() {
        super.onStart()
        coinViewModel.loadCoin()
    }

    override fun onStop() {
        coinViewModel.onStop()
        super.onStop()
    }

    private fun init() {
        viewDataBinding.recyclerviewMainTickerList.adapter = CoinAdapter()

        coinViewModel.showCoinMarketDialog.observe(this, Observer {
            coinMarketDialog.show(supportFragmentManager, null)
        })

        coinViewModel.showCoinSearchDialog.observe(this, Observer {
            val coinSearchDialog = CoinSearchDialog()
            coinSearchDialog.show(supportFragmentManager, null)
        })

        coinViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this@CoinListActivity, it, Toast.LENGTH_SHORT).show()
        })

    }
}