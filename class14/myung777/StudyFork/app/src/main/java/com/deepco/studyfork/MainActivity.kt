package com.deepco.studyfork

import android.os.Bundle
import com.deepco.studyfork.api.RetrofitService
import com.deepco.studyfork.data.model.Item
import com.deepco.studyfork.data.remote.RemoteMovieDataImpl
import com.deepco.studyfork.data.repository.RepositoryMovieDataImpl
import com.deepco.studyfork.presenter.MainContract
import com.deepco.studyfork.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {
    private lateinit var api: RetrofitService
    private lateinit var recyclerAdapterMovie: RecyclerAdapterMovie
    private val mainPresenter by lazy {
        val repositoryMovieDataImpl = RepositoryMovieDataImpl(RemoteMovieDataImpl(api))
        MainPresenter(this, repositoryMovieDataImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api = RetrofitService.create()
        setRecyclerView()
        search_btn.setOnClickListener {
            val query = movie_edit_text.text.toString()
            mainPresenter.queryMovie(query)
        }
    }

    private fun setRecyclerView() {
        recyclerAdapterMovie = RecyclerAdapterMovie()
        recycler_view.adapter = recyclerAdapterMovie
    }

    override fun setMovieList(list: List<Item>) {
        recyclerAdapterMovie.setItemList(list)
    }
}