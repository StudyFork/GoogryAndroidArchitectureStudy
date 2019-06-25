package dev.daeyeon.gaasproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dev.daeyeon.gaasproject.R
import dev.daeyeon.gaasproject.ui.ticker.TickerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace(
                R.id.container,
                supportFragmentManager.findFragmentByTag(TickerFragment.TAG) ?: TickerFragment.newInstance(),
                TickerFragment.TAG
            )
        }
    }
}
