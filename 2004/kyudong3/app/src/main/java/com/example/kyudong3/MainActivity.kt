package com.example.kyudong3

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kyudong3.adapter.SearchMovieRvAdapter
import com.example.kyudong3.model.MovieList
import com.example.kyudong3.network.NaverApiServiceImpl
import com.example.kyudong3.util.RecyclerViewItemDivider
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val movieRvAdapter: SearchMovieRvAdapter by lazy {
        SearchMovieRvAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovieRecyclerView()

        searchETxt.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_UNSPECIFIED, EditorInfo.IME_ACTION_GO -> {
                    if (searchETxt.text.trim().isEmpty()) {
                        Toast.makeText(applicationContext, "검색어를 1자 이상 입력해주세요!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        fetchSearchMovieApi(searchETxt.text.trim().toString())
                    }
                    true
                }
                else -> {
                    false
                }
            }
        }

        searchBtn.setOnClickListener {
            if (searchETxt.text.trim().isEmpty()) {
                Toast.makeText(applicationContext, "검색어를 1자 이상 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                fetchSearchMovieApi(searchETxt.text.trim().toString())
            }
        }
    }

    private fun setMovieRecyclerView() {
        searchRv.apply {
            adapter = movieRvAdapter
            addItemDecoration(RecyclerViewItemDivider(this@MainActivity))
        }
    }

    private fun fetchSearchMovieApi(searchQuery: String) {
        val naverApiServiceImpl: NaverApiServiceImpl = NaverApiServiceImpl()
        val naverApiServiceCall = naverApiServiceImpl.getSearchMovie(searchQuery)

        naverApiServiceCall.enqueue(object : Callback<MovieList> {
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                if (response.body() != null) {
                    val movieList = response.body()

                    movieList?.run {
                        if (items.isEmpty()) {
                            Toast.makeText(applicationContext, "검색결과가 없습니다", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            movieRvAdapter.setMovieList(items)
                            movieRvAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }
}
