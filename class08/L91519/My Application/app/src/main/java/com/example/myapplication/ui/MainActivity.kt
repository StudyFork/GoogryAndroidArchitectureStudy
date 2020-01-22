package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.data.model.MovieResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter by lazy { MainPresenter(this) }
    private val adapter =
        MovieRecyclerViewAdpater() { link ->
            webview_detail_movie.loadUrl(link)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movie_list.adapter = adapter
        rv_movie_list.hasFixedSize()

        btn_movie_search.setOnClickListener {
            findMovie(et_movie.text.toString())
        }
    }

    override fun updateMovieRecycler(items: List<MovieResult.Item>) {
        adapter.setItems(items)
    }

    override fun failMovieGet(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun findMovie(query: String) {
        presenter.findMovie(query)
    }

    override fun queryNone() {
        Toast.makeText(applicationContext, getString(R.string.query_none), Toast.LENGTH_SHORT).show()
    }

    override fun resultNone() {
        Toast.makeText(applicationContext, getString(R.string.result_none), Toast.LENGTH_SHORT).show()
    }
}

