package io.github.sooakim.ui.movie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.github.sooakim.R
import io.github.sooakim.ui.base.SAActivity
import io.github.sooakim.ui.movie.adapter.SAMovieSearchResultAdapter
import io.github.sooakim.ui.movie.model.SAMoviePresentation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SAMovieSearchActivity : SAActivity<SAMovieSearchPresenter>(), SAMovieSearchContractor.View {
    private lateinit var searchEdit: AppCompatEditText
    private lateinit var searchButton: AppCompatButton
    private lateinit var searchResultRecyclerView: RecyclerView
    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var searchResultAdapter: SAMovieSearchResultAdapter

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override val presenter: SAMovieSearchPresenter by lazy {
        SAMovieSearchPresenter(
            movieRepository = requireApplication().movieRepository,
            view = this
        )
    }

    override val commonProgressView: View?
        get() = loadingProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_movie_search)

        initView()
        initRecyclerView()

        bindRx()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initView() {
        searchEdit = findViewById(R.id.et_search)
        searchButton = findViewById(R.id.btn_search)
        searchResultRecyclerView = findViewById(R.id.rv_search_result)
        loadingProgressBar = findViewById(R.id.pgb_loading)
    }

    private fun initRecyclerView() {
        if (!::searchResultAdapter.isInitialized) {
            searchResultAdapter =
                SAMovieSearchResultAdapter(
                    presenter::onSearchResultClick
                )
        }
        searchResultRecyclerView.adapter = searchResultAdapter
    }

    private fun bindRx() {
        searchEdit.textChanges()
            .map(CharSequence::toString)
            .subscribe(presenter::onSearchChanges)
            .addTo(compositeDisposable)

        searchButton.clicks()
            .subscribe { presenter.search(text = searchEdit.text.toString()) }
            .addTo(compositeDisposable)
    }

    override fun setSearchText(text: String) {
        searchEdit.setText(text)
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
