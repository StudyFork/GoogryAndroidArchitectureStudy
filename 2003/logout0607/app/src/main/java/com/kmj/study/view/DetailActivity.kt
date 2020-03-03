package com.kmj.study.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kmj.study.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var link = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent?.let {
            link = intent.getStringExtra("link")!!
        }

        initView()
    }

    private fun initView() {
        wv_view.loadUrl(link)
    }
}
