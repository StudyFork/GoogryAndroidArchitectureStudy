package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.CoinListPagerAdapter
import ado.sabgil.studyproject.databinding.ActivityMainBinding
import ado.sabgil.studyproject.view.base.BaseActivity
import ado.sabgil.studyproject.view.coinlist.CoinListFragment
import android.os.Bundle
import android.view.View
import android.widget.Toast

class HomeActivity : BaseActivity<ActivityMainBinding>(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomePresenter(this)
        presenter.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unSubscribe()
    }

    override fun updateViewPager(marketList: List<String>) {
        binding.vpCoinList.apply {
            adapter = CoinListPagerAdapter(
                supportFragmentManager,
                marketList.map { it to CoinListFragment.newInstance(it) }.toMap()
            )

            offscreenPageLimit = marketList.size - 1
        }

        binding.tlCoinList.setupWithViewPager(binding.vpCoinList)
    }

    override fun showProgressBar(flag: Boolean) {
        binding.pgHome.visibility = if (flag) View.VISIBLE else View.GONE
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
