package kr.schoolsharing.coinhelper.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.data.Repository


class MainActivity : AppCompatActivity() {

    private lateinit var upbitPresenter: UpbitPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val upbiFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
//                as UpbitFragment? ?: UpbitFragment.newInstance().also {
//            replaceFragmentInActivity(it,R.id.contentFrame)
//        }
        val upbitFragment = UpbitFragment.newInstance()

        upbitPresenter = UpbitPresenter(Repository, upbitFragment)

        adaptPager()

    }

    private fun adaptPager() {
        PagerAdapter(supportFragmentManager).apply {
            viewpager_main.adapter = this
            tabs_main.setupWithViewPager(viewpager_main)
        }
    }

}
