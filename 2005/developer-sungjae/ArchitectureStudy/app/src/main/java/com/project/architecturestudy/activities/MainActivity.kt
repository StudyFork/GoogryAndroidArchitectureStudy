package com.project.architecturestudy.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.project.architecturestudy.R
import com.project.architecturestudy.adapters.SearchAdapter
import com.project.architecturestudy.components.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: SearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            if (et_search.text.toString().isEmpty()) {
                return@setOnClickListener
            } else if (adapter?.itemCount ?: 0 > 0) {
                removeAllItems()
            }

            doSearch(et_search.text.toString())
        }
        setRecyclerView()
    }

    private fun removeAllItems() {
        adapter?.clear()
    }

    @SuppressLint("CheckResult")
    fun doSearch(keyWord: String) {
        val service = RetrofitService.create()
        service.getMovies(keyWord)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ movie ->
                adapter?.addData(movie.items)
            },
                { error ->
                    Log.d("error", error.toString())
                })
    }

    private fun setRecyclerView() {
        adapter = SearchAdapter()
        listview_movie.adapter = adapter
    }
}
