package dev.daeyeon.gaasproject.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.ui.ticker.TickerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(
                        R.id.container,
                        TickerFragment.newInstance()
                )
                .commit()
    }
}
