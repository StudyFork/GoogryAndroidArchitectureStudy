package io.github.sooakim.ui

import android.content.Intent
import android.net.Uri
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
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class SAMainActivity : AppCompatActivity(),
    SAMainSearchResultAdapter.OnMainSearchResultClickListener {
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
        mSearchResultAdapter.clickListener = null
        mCompositeDisposable.clear()
        super.onDestroy()
    }

    private fun initView() {
        mSearchEdit = findViewById(R.id.et_search)
        mSearchButton = findViewById(R.id.btn_search)
        mSearchResultRecyclerView = findViewById(R.id.rv_search_result)
        mLoadingProgressBar = findViewById(R.id.pgb_loading)
    }

    private fun showLoading() {
        mLoadingProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        mLoadingProgressBar.visibility = View.INVISIBLE
    }

    private fun initRecyclerView() {
        mSearchResultAdapter.clickListener = this
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
            .doOnNext { _ -> showLoading() }
            .switchMapSingle(SANetworkService.movieApi::getSearchMovie)
            .map(SANaverSearchResponse<SAMovieModel>::items)
            .onErrorReturn { _ -> listOf() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { _ -> hideLoading() }
            .subscribe(mSearchResultAdapter::submitList)
            .addTo(mCompositeDisposable)
    }

    override fun onSearchResultClick(item: SAMovieModel) {
        val linkString = item.link.takeIf(String::isNotBlank) ?: return

        Intent(Intent.ACTION_VIEW, Uri.parse(linkString)).takeIf {
            it.resolveActivity(packageManager) != null
        }?.run(this::startActivity)
    }
}
