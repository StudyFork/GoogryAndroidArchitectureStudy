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
import io.github.sooakim.network.model.SAMovieModel
import io.github.sooakim.network.model.response.SANaverSearchResponse
import io.github.sooakim.ui.base.SAActivity
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class SAMovieSearchActivity : SAActivity() {
    private lateinit var searchEdit: AppCompatEditText
    private lateinit var searchButton: AppCompatButton
    private lateinit var searchResultRecyclerView: RecyclerView
    private lateinit var loadingProgressBar: ProgressBar

    private lateinit var searchResultAdapter: SAMovieSearchResultAdapter
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override val commonProgressView: View?
        get() = loadingProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        initView()
        initRecyclerView()
        bindRx()
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
                SAMovieSearchResultAdapter(this::onSearchResultClick)
        }
        searchResultRecyclerView.adapter = searchResultAdapter
    }

    private fun bindRx() {
        val textChanges = searchEdit.textChanges()
        val buttonClick = searchButton.clicks()
            .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .flatMapSingle { Single.fromCallable(searchEdit::getText) }

        Observable.merge(textChanges, buttonClick)
            .debounce(700, TimeUnit.MILLISECONDS)
            .filter(CharSequence::isNotBlank)
            .map(CharSequence::trim)
            .map(CharSequence::toString)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { showLoading() }
            .switchMapSingle(requireApplication().networkService.movieApi::getSearchMovie)
            .map(SANaverSearchResponse<SAMovieModel>::items)
            .onErrorReturn { listOf() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { hideLoading() }
            .subscribe(searchResultAdapter::submitList)
            .addTo(compositeDisposable)
    }

    private fun onSearchResultClick(item: SAMovieModel) {
        val linkString = item.link.takeIf(String::isNotBlank) ?: return

        Intent(Intent.ACTION_VIEW, Uri.parse(linkString)).takeIf {
            it.resolveActivity(packageManager) != null
        }?.run(this::startActivity)
    }
}
