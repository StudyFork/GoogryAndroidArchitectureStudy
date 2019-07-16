package com.example.architecturestudy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //뷰페이저 어댑터 연결
        viewPager.adapter = MainAdapter(supportFragmentManager)

        //탭 레이아웃에 뷰페이저 연결
        tabLayout.setupWithViewPager(viewPager)
    }
}
