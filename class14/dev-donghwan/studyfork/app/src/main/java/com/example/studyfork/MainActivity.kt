package com.example.studyfork

import android.os.Bundle
import com.example.studyfork.base.BaseActivity
import com.example.studyfork.data.model.MovieSearchResponse
import com.example.studyfork.data.remote.RemoteDataSourceImpl
import com.example.studyfork.data.repository.RepositoryImpl
import com.example.studyfork.databinding.ActivityMainBinding

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    MainContract.View {

    private val presenter: MainContract.Presenter = MainPresenter(
        this,
        RepositoryImpl(RemoteDataSourceImpl())
    )

    private val recyclerAdapter = MovieRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recMovie.adapter = recyclerAdapter

        binding.btnSearch.setOnClickListener {
            binding.edtQuery.text.toString().run {
                presenter.requestMovieList(this)
            }
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun showQueryEmpty() {
        showToast("검색어를 입력해주세요")
    }

    override fun showMovieEmpty() {
        showToast("검색 결과가 없습니다.")
    }

    override fun showMovieError() {
        showToast("데이터를 불러오는 중에 문가 발생했습니다.")
    }

    override fun showMovieList(list: List<MovieSearchResponse.MovieItem>) {
        recyclerAdapter.itemChange(list)
    }

}