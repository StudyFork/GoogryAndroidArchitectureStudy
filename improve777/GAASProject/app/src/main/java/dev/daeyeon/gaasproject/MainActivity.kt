package dev.daeyeon.gaasproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dev.daesin.gaasdy.TickerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TickerFragment.newInstance())
            .commit()
    }
}
