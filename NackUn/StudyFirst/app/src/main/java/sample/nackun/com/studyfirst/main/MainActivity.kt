package sample.nackun.com.studyfirst.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBtnClick()

        //supportFragmentManager.beginTransaction().replace(R.id.mainFrame, MainFragment()).commit()

        marketKRW.callOnClick()
    }

    fun setBtnClick() {
        val onClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                marketKRW.setTextColor(ContextCompat.getColor(baseContext, R.color.grey))
                marketBTC.setTextColor(ContextCompat.getColor(baseContext, R.color.grey))
                marketETH.setTextColor(ContextCompat.getColor(baseContext, R.color.grey))
                marketUSDT.setTextColor(ContextCompat.getColor(baseContext, R.color.grey))
                var selectedMarket = v as TextView
                selectedMarket.setTextColor(ContextCompat.getColor(baseContext,
                    R.color.indigo
                ))
                //initData(selectedMarket.text.toString())
            }
        }

        marketKRW.setOnClickListener(onClickListener)
        marketBTC.setOnClickListener(onClickListener)
        marketETH.setOnClickListener(onClickListener)
        marketUSDT.setOnClickListener(onClickListener)
    }
}