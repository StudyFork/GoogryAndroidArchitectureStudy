package m.woong.architecturestudy.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import m.woong.architecturestudy.*
import m.woong.architecturestudy.data.source.remote.model.MovieResponse
import m.woong.architecturestudy.ui.adapter.MovieAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main), MovieContract.View {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MovieAdapter
    private lateinit var appContainer: AppContainer
    private lateinit var moviePresenter: MoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = (application as MyApplication).appContainer
        moviePresenter = MoviePresenter(this, appContainer.movieRepository)
        viewAdapter = MovieAdapter()
        recyclerView = findViewById<RecyclerView>(R.id.movie_rv).apply {
            setHasFixedSize(true)
            adapter = viewAdapter
        }

        movie_search_btn.setOnClickListener {
            moviePresenter.requestMovieList(movie_search_et.text.toString())
        }
    }

    override fun showMovieList(movieResponse: MovieResponse) {
        viewAdapter.resetData(movieResponse.items)
    }

    override fun showErrorEmptyQuery() {
        Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_LONG).show()
    }

    override fun showErrorResponseMsg(t: Throwable) {
        Toast.makeText(this, "응답을 받아오지 못했습니다.\n 에러코드:$t", Toast.LENGTH_LONG).show()
    }
}
