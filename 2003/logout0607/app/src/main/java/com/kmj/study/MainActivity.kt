package com.kmj.study

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmj.study.dto.MovieDto
import com.kmj.study.dto.MovieResponseDto
import com.kmj.study.repository.SearchRepository
import com.kmj.study.view.DetailActivity
import com.kmj.study.view.adapter.MainRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var searchRepository = SearchRepository
    private var searchItems = ArrayList<MovieDto>()
    private lateinit var searchRecyclerAdapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        btn_search.setOnClickListener {
            if (et_search.text.toString().isNotBlank()) {
                getSearchItems(et_search.text.toString())
            }
        }

        recycler_view_main.run {
            searchRecyclerAdapter = MainRecyclerAdapter(this@MainActivity, searchItems) {
                if (it.isNotBlank()) {
                    Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra("link", it)
                        startActivity(this)
                    }
                }
            }

            adapter = searchRecyclerAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getSearchItems(query: String) {
        searchRepository.getSearchItems(query = query).enqueue(object : Callback<MovieResponseDto> {
            override fun onFailure(call: Call<MovieResponseDto>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MovieResponseDto>,
                response: Response<MovieResponseDto>
            ) {
//                Log.d("dd", response.body().toString())
                response.body()?.let {

                    searchItems.clear()
                    it.items.forEach {
                        searchItems.add(it)
                    }

                    searchRecyclerAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}
