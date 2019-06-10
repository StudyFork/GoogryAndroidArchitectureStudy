package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.CoinListPagerAdapter
import ado.sabgil.studyproject.data.CoinRepositoryImpl
import ado.sabgil.studyproject.databinding.ActivityMainBinding
import ado.sabgil.studyproject.view.base.BaseActivity
import ado.sabgil.studyproject.view.coinlist.CoinListFragment
import ado.sabgil.studyproject.view.searchcoin.SearchCoinActivity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer

class HomeActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressBar = binding.pgHome

        homeViewModel = addingToContainer {
            HomeViewModel(CoinRepositoryImpl)
        }

        homeViewModel.marketList.observe(this, Observer<List<String>>(::updateViewPager))

        registerEvent()

        homeViewModel.loadMarketList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search -> {
                startActivity(Intent(this, SearchCoinActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerEvent() {
        homeViewModel.run {
            showToastEvent.observe(this@HomeActivity, Observer(::showToastMessage))

            isLoading.observe(this@HomeActivity, Observer {
                if (it) showProgressBar() else hideProgressBar()
            })
        }
    }

    private fun updateViewPager(marketList: List<String>) {
        bind {
            vpCoinList.apply {
                adapter = CoinListPagerAdapter(
                    supportFragmentManager,
                    marketList.map {
                        it to CoinListFragment.newInstance(it)
                    }.toMap()
                )
                offscreenPageLimit = marketList.size - 1
            }
            tlCoinList.setupWithViewPager(vpCoinList)
        }
    }
}
