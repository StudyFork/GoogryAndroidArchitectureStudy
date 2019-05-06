package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.R
import ado.sabgil.studyproject.adapter.TickerAdapter
import ado.sabgil.studyproject.databinding.ActivityMainBinding
import ado.sabgil.studyproject.enums.BaseCurrency
import ado.sabgil.studyproject.view.base.BaseActivity
import ado.sabgil.studyproject.view.coinlist.CoinListFragment
import android.os.Bundle

class HomeActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // TODO : delete this code block
        supportFragmentManager.beginTransaction().add(
            R.id.fl_container,
            CoinListFragment.newInstance(BaseCurrency.KRW)
        ).commit()
    }
}
