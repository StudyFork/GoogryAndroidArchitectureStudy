package me.hoyuo.myapplication.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import me.hoyuo.myapplication.R
import me.hoyuo.myapplication.ui.coinlist.CoinListFragment
import timber.log.Timber

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag(TAG).d("onCreate")
        setContentView(R.layout.activity_main)

        val coinListFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                ?: CoinListFragment.newIntent()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.contentFrame, coinListFragment)
        transaction.commit()
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}
