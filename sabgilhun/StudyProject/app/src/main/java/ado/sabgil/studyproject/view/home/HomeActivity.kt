package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.CoinListPagerAdapter
import ado.sabgil.studyproject.data.CoinRepositoryImpl
import ado.sabgil.studyproject.databinding.ActivityMainBinding
import ado.sabgil.studyproject.view.base.BaseActivity
import ado.sabgil.studyproject.view.coinlist.CoinListFragment
import android.os.Bundle
import android.view.View
import android.widget.Toast

class HomeActivity : BaseActivity<ActivityMainBinding, HomeContract.Presenter>(R.layout.activity_main),
    HomeContract.View {

    override fun createPresenter(): HomeContract.Presenter = HomePresenter(this, CoinRepositoryImpl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.loadMarketList()
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
}
