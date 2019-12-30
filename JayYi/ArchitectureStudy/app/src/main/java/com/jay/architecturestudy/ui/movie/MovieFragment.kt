package com.jay.architecturestudy.ui.movie

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Movie
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : BaseFragment(R.layout.fragment_movie), MovieContract.View {
    override val presenter: MovieContract.Presenter by lazy {
        MoviePresenter(this, naverSearchRepository)
    }

    private lateinit var movieAdapter: MovieAdapter

    private val naverSearchRepository by lazy {
        NaverSearchRepositoryImpl()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { activity ->
            movieAdapter = MovieAdapter()
            recycler_view.run {
                adapter = movieAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }

        search_bar.onClickAction = { keyword ->
            search(keyword)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun search(keyword: String) {
        presenter.search(keyword)
    }

    override fun updateResult(result: List<Movie>) {
        movieAdapter.setData(result)
    }
}