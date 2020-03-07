package com.example.kangraemin

import android.content.Intent
import android.os.Bundle
import com.example.kangraemin.base.KangBaseActivity
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class SplashActivity : KangBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashTimer = Completable
            .timer(3, TimeUnit.SECONDS)
            .doOnComplete {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .subscribe()
        compositeDisposable.add(splashTimer)
    }
}
