package com.example.kangraemin

import android.content.Intent
import android.os.Bundle
import com.example.kangraemin.base.KangBaseActivity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SplashActivity : KangBaseActivity() {

    var splashTime = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashTimer = Observable
            .interval(1, TimeUnit.SECONDS)
            .takeWhile { splashTime > 0 }
            .doOnComplete {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .subscribe {
                splashTime--
            }
        compositeDisposable.add(splashTimer)
    }
}
