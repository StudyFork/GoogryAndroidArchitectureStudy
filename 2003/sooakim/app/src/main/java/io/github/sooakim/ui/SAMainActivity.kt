package io.github.sooakim.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.github.sooakim.R
import io.github.sooakim.network.SANetworkService
import io.github.sooakim.network.model.SAMovieModel
import io.github.sooakim.network.model.response.SANaverSearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SAMainActivity : AppCompatActivity() {
    private lateinit var mSearchEdit: AppCompatEditText
    private lateinit var mSearchButton: AppCompatButton
    private lateinit var mSearchResultRecyclerView: RecyclerView
    private lateinit var mLoadingProgressBar: ProgressBar

    private val mSearchResultAdapter: SAMainSearchResultAdapter = SAMainSearchResultAdapter()
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        bindRx()
    }

    override fun onDestroy() {
        mCompositeDisposable.clear()
        super.onDestroy()
    }

    private fun initView() {
        mSearchEdit = findViewById(R.id.et_search)
        mSearchButton = findViewById(R.id.btn_search)
        mSearchResultRecyclerView = findViewById(R.id.rv_search_result)
        mLoadingProgressBar = findViewById(R.id.pgb_loading)
    }

    private fun updateLoading(isLoading: Boolean) {
        mLoadingProgressBar.visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    private fun initRecyclerView() {
        mSearchResultRecyclerView.adapter = mSearchResultAdapter
    }

    private fun bindRx() {
        val textChanges = mSearchEdit.textChanges()
        val buttonClick = mSearchButton.clicks()
            .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .flatMapSingle { Single.fromCallable(mSearchEdit::getText) }

        Observable.merge(textChanges, buttonClick)
            .debounce(700, TimeUnit.MILLISECONDS)
            .filter(CharSequence::isNotBlank)
            .map(CharSequence::trim)
            .map(CharSequence::toString)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { _ -> updateLoading(true) }
            .switchMapSingle(SANetworkService.movieApi::getSearchMovie)
            .map(SANaverSearchResponse<SAMovieModel>::items)
            .onErrorReturn { _ -> listOf() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { _ -> updateLoading(false) }
            .doOnNext(mSearchResultAdapter::submitList)
            .subscribe()
            .let(mCompositeDisposable::add)
    }
}
