package com.project.architecturestudy.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.architecturestudy.R
import com.project.architecturestudy.adapters.SearchAdapter
import com.project.architecturestudy.components.RetrofitService
import com.project.architecturestudy.models.MovieData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val searchData: ArrayList<MovieData.Items> = ArrayList()
    private var adapter: SearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            if (et_search.text.toString().isEmpty()) {

                return@setOnClickListener

            } else if (searchData.count() > 0) {

                removeAllItems()
                doSearch(et_search.text.toString())
            }
        }
        setRecyclerView()
    }

    private fun removeAllItems() {
        searchData.clear()
        adapter?.notifyItemRangeRemoved(0, searchData.count() - 1)
    }

    @SuppressLint("CheckResult")
    fun doSearch(keyWord: String) {
        val service = RetrofitService.create()
        service.getMovies(keyWord)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ movie ->
                searchData.addAll(movie.items)
                adapter?.notifyDataSetChanged()
            },
                { error ->
                    Log.d("error", error.toString())
                }
            )
    }

    private fun setRecyclerView() {
        listview_movie.layoutManager = LinearLayoutManager(this)
        adapter = SearchAdapter(searchData)
        listview_movie.adapter = adapter
    }
}
