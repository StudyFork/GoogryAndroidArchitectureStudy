package com.example.architecturestudy.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.architecturestudy.R
import com.example.architecturestudy.base.BaseActivity
import com.example.architecturestudy.databinding.ActivitySplashBinding
import com.example.architecturestudy.ui.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 메인엑티비티로 이동
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))

        finish() // 현재액티비티 종료
    }
}
