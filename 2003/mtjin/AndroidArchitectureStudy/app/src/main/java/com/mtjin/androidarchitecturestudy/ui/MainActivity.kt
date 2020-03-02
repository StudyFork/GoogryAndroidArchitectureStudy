package com.mtjin.androidarchitecturestudy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.androidarchitecturestudy.Movie
import com.mtjin.androidarchitecturestudy.MovieAdapter
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.api.ApiClient
import com.mtjin.androidarchitecturestudy.api.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity(),
    MovieAdapter.ItemClickListener {

    private lateinit var etInput: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovies: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initListener()
    }

    fun initView() {
        etInput = findViewById(R.id.et_input)
        btnSearch = findViewById(R.id.btn_search)
        rvMovies = findViewById(R.id.rv_movies)
        movieAdapter = MovieAdapter()
        rvMovies.adapter = movieAdapter
    }

    private fun initListener() {
        //어댑터 아이템 클릭리스너
        movieAdapter.setItemClickListener(this)
        //검색버튼
        btnSearch.setOnClickListener {
            var query = etInput.text.toString().trim()
            if (query.isEmpty()) {
                onToastMessage("검색어를 입력해주세요.")
            } else {
                onToastMessage("잠시만 기다려주세요.")
                movieAdapter.clear()
                val apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
                apiInterface.getSearchMovie(
                    query
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        // handle success
                        movieAdapter.setItems(it.movies)
                        movieAdapter.notifyDataSetChanged()
                    }, {
                        // handle fail
                        onToastMessage("불러오는데 실패 했습니다.")
                    })

            }
        }
    }

    override fun onItemClick(movie: Movie) {
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.link))
        startActivity(intent)
    }

    fun onToastMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
