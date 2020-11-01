package com.example.studyfork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studyfork.model.Network
import com.example.studyfork.model.toDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val recyclerAdapter by lazy { MovieRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }

        rec_movie.adapter = recyclerAdapter

        btn_search.setOnClickListener {
            edt_query.text?.run {
                Network.naverApi.searchMovie(this.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { response ->
                        recyclerAdapter.itemChange(response.toDomain())
                    }
            }
        }
    }
}