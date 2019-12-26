package com.example.studyapplication.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.studyapplication.R
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSourceImpl
import com.example.studyapplication.data.model.SearchMovieResult
import com.example.studyapplication.data.repository.NaverSearchRepository
import com.example.studyapplication.data.repository.NaverSearchRepositoryImpl
import com.example.studyapplication.main.movie.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(), Contract.View {
    lateinit var movieAdapter: MovieAdapter
    private val repository: NaverSearchRepository = NaverSearchRepositoryImpl(NaverRemoteDataSourceImpl())
    private lateinit var presenter : Contract.UserActions

//    private val presenter by lazy {
//        Presenter(this, repository)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = Presenter(this, repository)

        btnSearch.setOnClickListener(btnSearchClickListener())
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
    }

    // 검색 버튼 클릭 리스너
    private fun btnSearchClickListener(): View.OnClickListener {
        return View.OnClickListener {
            val movieTitle = etQuery.text.toString()
            presenter.clickSearchButton(movieTitle)
        }
    }

//    // 영화 검색 요청
//    private fun requestSearchMovie(title: String) {
//        repository.getMovieList(title, object : Conn {
//            override fun <T> success(result: T) {
//                val searchData : SearchMovieResult? = result as SearchMovieResult
//                searchData?.let {
//                    movieAdapter.resetItem(searchData.arrMovieInfo)
//                }
//            }
//
//            override fun failed() {
//                TODO()
//            }
//        })
//    }

    companion object {
        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }

    override fun showList(items: Array<SearchMovieResult.MovieInfo>) {
        movieAdapter.resetItem(items)
    }
}
