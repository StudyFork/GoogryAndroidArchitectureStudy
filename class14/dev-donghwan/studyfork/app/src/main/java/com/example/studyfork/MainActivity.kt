package com.example.studyfork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studyfork.model.RemoteDataSourceImpl
import com.example.studyfork.model.RepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val repository by lazy {
        RepositoryImpl(RemoteDataSourceImpl())
    }
    private val recyclerAdapter by lazy { MovieRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rec_movie.adapter = recyclerAdapter

        btn_search.setOnClickListener {
            edt_query.text?.run {
                repository.searchMovie(this.toString(),
                    {
                        it.items.run {
                            recyclerAdapter.itemChange(this)
                        }
                    }, {
                        it.printStackTrace()
                    })
            }
        }
    }

    override fun onDestroy() {
        repository.disposableClear()
        super.onDestroy()
    }
}