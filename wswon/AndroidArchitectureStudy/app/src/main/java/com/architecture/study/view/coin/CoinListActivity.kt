package com.architecture.study.view.coin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.architecture.study.R
import com.architecture.study.data.repository.CoinRepositoryImpl
import com.architecture.study.databinding.ActivityMainBinding
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.util.Injection
import com.architecture.study.viewmodel.MarketViewModel
import com.google.android.material.tabs.TabLayout


class CoinListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val marketViewModel by lazy {
        MarketViewModel(CoinRepositoryImpl.getInstance(Injection.provideCoinRemoteDataSource()))
    }

    private val tabList = listOf(
        R.string.monetary_unit_1,
        R.string.monetary_unit_2,
        R.string.monetary_unit_3,
        R.string.monetary_unit_4
    )

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        marketViewModel.run {
            getMarketList()

            exceptionMessage.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    (sender as? ObservableField<String>)
                        ?.get()
                        ?.let {
                            showMessage(it)
                        }
                }
            })

            marketList.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    (sender as? ObservableField<List<MarketResponse>>)
                        ?.get()
                        ?.let {
                            setTabPager(it)
                        }
                }
            })
        }


    }

    private fun showMessage(message: String) {
        Toast.makeText(this@CoinListActivity, message, Toast.LENGTH_SHORT).show()
    }

    /* tab layout && view pager init*/
    private fun setTabPager(marketList: List<MarketResponse>) {

        binding.run {
            tabLayoutMonetaryUnit.setupWithViewPager(viewPagerCoinList)

            viewPagerCoinList.run {
                adapter = object : FragmentPagerAdapter(
                    supportFragmentManager,
                    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
                ) {

                    override fun getItem(position: Int): Fragment =
                        CoinListFragment.newInstance(
                            marketList
                                .asSequence()
                                .filter { it.market.split("-")[0] == context.getString(tabList[position]) }
                                .map { it.market }
                                .toList()
                        )


                    override fun getCount(): Int =
                        tabList.size

                    override fun getPageTitle(position: Int): CharSequence? =
                        getString(tabList[position])
                }
                addOnPageChangeListener(
                    TabLayout.TabLayoutOnPageChangeListener(
                        tabLayoutMonetaryUnit
                    )
                )
            }
        }
    }
}
