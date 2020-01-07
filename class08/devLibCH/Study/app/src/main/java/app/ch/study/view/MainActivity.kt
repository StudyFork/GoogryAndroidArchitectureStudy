package app.ch.study.view

import android.os.Bundle
import android.os.PersistableBundle
import app.ch.study.R
import app.ch.study.core.BaseActivity
import app.ch.study.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding.lifecycleOwner = this

        initLayout()
    }

    private fun initLayout() {

    }
}
