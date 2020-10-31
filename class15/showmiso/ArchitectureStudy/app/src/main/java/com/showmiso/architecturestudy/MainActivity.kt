package com.showmiso.architecturestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.showmiso.architecturestudy.api.APIInterface
import com.showmiso.architecturestudy.api.RetrofitClient

class MainActivity : AppCompatActivity() {

    private lateinit var api: APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initService()

        initUI()
    }

    private fun initService() {
        api = RetrofitClient.createService(
            getString(R.string.searchMoveUrl),
            getString(R.string.clientId),
            getString(R.string.clientSecret)
        )
    }

    private fun initUI() {

    }
}