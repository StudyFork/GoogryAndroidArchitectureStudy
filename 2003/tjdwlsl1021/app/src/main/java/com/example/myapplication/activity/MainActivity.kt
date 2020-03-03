package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R


// 2020-02-29 hsj 프로젝트 생성

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //-네이버 영화검색 api호출하는 화면으로 이동
        var intent: Intent = Intent(this, SearchMovieActivity::class.java)
        startActivity(intent)
    }
}
