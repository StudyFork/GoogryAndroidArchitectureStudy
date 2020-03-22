package io.github.sooakim.ui.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.github.sooakim.R
import io.github.sooakim.databinding.ActivityMovieSearchBinding
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.movie.adapter.SAMovieSearchResultAdapter
import io.github.sooakim.ui.movie.model.SAMoviePresentation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SAMovieSearchActivity :
    SAActivity<ActivityMovieSearchBinding, SAMovieSearchPresenter>(R.layout.activity_movie_search),
    SAMovieSearchContractor.View {
    private lateinit var searchResultAdapter: SAMovieSearchResultAdapter

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override val presenter: SAMovieSearchPresenter by lazy {
        SAMovieSearchPresenter(
            movieRepository = requireApplication().movieRepository,
            view = this
        )
    }

    override val commonProgressView: View?
        get() = viewDataBinding.pgbLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()

        bindRx()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initRecyclerView() {
        if (!::searchResultAdapter.isInitialized) {
            searchResultAdapter =
                SAMovieSearchResultAdapter(
                    presenter::onSearchResultClick
                )
        }
        viewDataBinding.rvSearchResult.adapter = searchResultAdapter
    }

    private fun bindRx() {
        viewDataBinding.etSearch.textChanges()
            .map(CharSequence::toString)
            .subscribe(presenter::onSearchChanges)
            .addTo(compositeDisposable)

        viewDataBinding.btnSearch.clicks()
            .subscribe { presenter.search(text = viewDataBinding.etSearch.text.toString()) }
            .addTo(compositeDisposable)
    }

    override fun setSearchText(text: String) {
        viewDataBinding.etSearch.setText(text)
    }

    override fun showSearchResults(results: List<SAMoviePresentation>) {
        searchResultAdapter.submitList(results)
    }

    override fun showLink(uri: Uri) {
        Intent(Intent.ACTION_VIEW, uri).takeIf {
            it.resolveActivity(packageManager) != null
        }?.run(this::startActivity)
    }
}
