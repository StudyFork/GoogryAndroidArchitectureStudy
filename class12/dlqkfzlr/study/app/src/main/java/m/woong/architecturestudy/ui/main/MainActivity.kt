package m.woong.architecturestudy.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import m.woong.architecturestudy.R
import m.woong.architecturestudy.data.repository.NaverRepository
import m.woong.architecturestudy.data.repository.NaverRepositoryImpl
import m.woong.architecturestudy.data.source.remote.MovieApi
import m.woong.architecturestudy.data.source.remote.NaverRemoteDataSource
import m.woong.architecturestudy.data.source.remote.NaverRemoteDataSourceImpl
import m.woong.architecturestudy.ui.adapter.MovieAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MovieAdapter

    private lateinit var service: MovieApi
    private lateinit var repository: NaverRepository
    private lateinit var remoteDataSource: NaverRemoteDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        service = MovieApi.MovieRetrofit.service
        remoteDataSource = NaverRemoteDataSourceImpl(service)
        repository = NaverRepositoryImpl(remoteDataSource)

        movie_search_btn.setOnClickListener {
            val keyword = movie_search_et.text.toString()
            if (keyword.isEmpty()) {
                Toast.makeText(
                    this@MainActivity,
                    "검색어를 입력해주세요",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                repository.getRecentMovie(keyword,
                    success = {
                        viewAdapter.resetData(it.items)
                    }, failure = {
                        Toast.makeText(
                            this@MainActivity,
                            "응답 실패 :$it",
                            Toast.LENGTH_SHORT
                        ).show()
                    })
            }
        }

        viewAdapter = MovieAdapter()
        recyclerView = findViewById<RecyclerView>(R.id.movie_rv).apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }

    }
}
