package com.ironelder.androidarchitecture.component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ironelder.androidarchitecture.R

class MainActivity : BaseActivity() {
    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
