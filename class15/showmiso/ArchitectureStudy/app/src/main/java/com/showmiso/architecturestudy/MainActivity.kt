package com.showmiso.architecturestudy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.showmiso.architecturestudy.api.APIInterface
import com.showmiso.architecturestudy.api.RetrofitClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var api: APIInterface
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initService()

        initUI()
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    private fun initService() {
        api = RetrofitClient.createService(
            getString(R.string.searchMoveUrl),
            getString(R.string.clientId),
            getString(R.string.clientSecret)
        )
    }

    private fun initUI() {

        api.getMovies("기생충")
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d(tag, "Success")
            }, {
                Log.e(tag, "Fail", it)
            })
            .addTo(disposable)

    }

    companion object {
        private val tag = MainActivity::class.java.simpleName
    }
}