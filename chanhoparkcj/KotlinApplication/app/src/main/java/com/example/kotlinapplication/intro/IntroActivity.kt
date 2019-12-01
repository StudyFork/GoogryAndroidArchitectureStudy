package com.example.kotlinapplication.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.kotlinapplication.MainActivity
import com.example.kotlinapplication.R

class IntroActivity : AppCompatActivity() {

    var handler = Handler()
    var r = Runnable {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

    override fun onResume() {
        super.onResume()
        //화면에 들어 왔을 경우 예약 걸어주기
        handler.postDelayed(r,4000)
    }

    override fun onPause() {
        super.onPause()
        //화면을 벗어나면 handler에 예약해 놓은 작업을 취소하자
        handler.removeCallbacks(r)
    }
}
