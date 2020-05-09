package com.tsdev.tsandroid.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.base.BaseActivity
import com.tsdev.tsandroid.constant.Const
import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.data.repository.NaverReopsitory
import com.tsdev.tsandroid.data.repository.NaverRepositoryImpl
import com.tsdev.tsandroid.databinding.ActivityMainBinding
import com.tsdev.tsandroid.presenter.MovieContract
import com.tsdev.tsandroid.presenter.MoviePresenter
import com.tsdev.tsandroid.provider.ResourceProviderImpl
import com.tsdev.tsandroid.ui.adapter.MovieRecyclerAdapter
import com.tsdev.tsandroid.ui.viewholder.MovieRecyclerViewViewHolder
import com.tsdev.tsandroid.util.MapConverter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : BaseActivity<MoviePresenter, ActivityMainBinding>(),
    MovieRecyclerViewViewHolder.OnClickDelegate, MovieContract.View {

    override val binding: ActivityMainBinding by movieSetDataBinding(R.layout.activity_main, this)
    {
        it.also {
            it.activity = this
            it.presenter = presenter
        }
    }

    private val movieRecyclerAdapter: MovieRecyclerAdapter by lazy {
        MovieRecyclerAdapter(this)
    }
    private val movieMapConverter: MapConverter by lazy {
        MapConverter()
    }
    private val naverRepository: NaverReopsitory by lazy {
        NaverRepositoryImpl(movieMapConverter)
    }

    override val presenter: MoviePresenter by lazy {
        MoviePresenter(this, naverRepository, ResourceProviderImpl(this), rxJavaEvent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        disposable.add(
            rxJavaEvent.getBackButtonEvent()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(2, 1)
                .map { it[0] to it[1] }
                .subscribe({
                    if (it.second - it.first > Const.BACK_BUTTON_THROTTLE_TIME)
                        showToastMessage(resources.getString(R.string.destroy_view_toast_message))
                    else
                        finish()
                }, {
                    it.printStackTrace()
                })
        )

        binding.movieRecycler.run {
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
            binding.searchImg.windowToken,
            0
        )
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showSearchResult(items: List<Item>) {
        items.takeIf (List<Item>::isNotEmpty)?.run {
            movieRecyclerAdapter.clear()
            movieRecyclerAdapter.addItems(items)
            movieRecyclerAdapter.notifiedDataChange()
        } ?: run {
            movieRecyclerAdapter.clear()
            showToastMessage(getString(R.string.non_search_result))
        }
    }

    override fun removeAll() {
        movieRecyclerAdapter.clear()
        movieRecyclerAdapter.notifiedDataChange()
    }

    override fun onBackPressed() {
        presenter.onPressBackButton()
    }
}
