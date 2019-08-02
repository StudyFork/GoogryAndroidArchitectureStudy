package com.example.architecturestudy.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturestudy.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 메인엑티비티로 이동
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))

        finish() // 현재액티비티 종료
    }
}
