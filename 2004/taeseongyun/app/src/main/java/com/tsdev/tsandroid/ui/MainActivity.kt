package com.tsdev.tsandroid.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.tsdev.tsandroid.api.NaverAPI
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.base.BaseActivity
import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.data.repository.NaverReopsitory
import com.tsdev.tsandroid.data.repository.NaverRepositoryImpl
import com.tsdev.tsandroid.presenter.MovieContract
import com.tsdev.tsandroid.presenter.MoviePresenter
import com.tsdev.tsandroid.provider.ResourceProvider
import com.tsdev.tsandroid.provider.ResourceProviderImpl
import com.tsdev.tsandroid.ui.adapter.MovieRecyclerAdapter
import com.tsdev.tsandroid.ui.viewholder.MovieRecyclerViewViewHolder
import com.tsdev.tsandroid.util.MapConverter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MovieRecyclerViewViewHolder.OnClickDelegate,
    MovieContract.View {

    private val movieRecyclerAdapter: MovieRecyclerAdapter by lazy {
        MovieRecyclerAdapter(this)
    }
    private val movieMapConverter: MapConverter by lazy {
        MapConverter()
    }
    private val naverRepository: NaverReopsitory by lazy {
        NaverRepositoryImpl(movieMapConverter)
    }

    private val naverMoviePresenter: MoviePresenter by lazy {
        MoviePresenter(this, disposable, naverRepository, ResourceProviderImpl(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_img.setOnClickListener {
            naverMoviePresenter.loadMovie(edit_query.text.toString())
        }

        movie_recycler.run {
            adapter = movieRecyclerAdapter
        }
    }

    override fun onClickEventListener(position: Int) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(movieRecyclerAdapter.itemList[position].link)
            )
        )
    }

    override fun onHideSoftKeyboard() {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            search_img.windowToken,
            0
        )
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun showSearchResult(items: List<Item>) {
        movieRecyclerAdapter.clear()
        movieRecyclerAdapter.addItems(items)
        movieRecyclerAdapter.notifiedDataChange()
    }
}
