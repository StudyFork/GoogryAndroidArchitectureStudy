package com.hansung.firstproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val clientId = "wqaH4p5lX5tCWVMOGv0y" // naver 검색API 사용을 위한 Client ID
    private val clientSecret = "cnOKERR71G" //naver 검색API 사용을 위한 Client Secret

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}