package com.architecturestudy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.architecturestudy.model.upbit.Common
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO 추후 제거 (테스트용)
        btn_test.setOnClickListener {
            Common().getMarketList("KRW")
        }
    }
}
