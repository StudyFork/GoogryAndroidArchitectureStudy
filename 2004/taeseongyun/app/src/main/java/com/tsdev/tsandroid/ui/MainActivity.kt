package com.tsdev.tsandroid.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tsdev.tsandroid.NaverAPI
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.data.repository.NaverRepositoryImpl
import com.tsdev.tsandroid.ui.adapter.MovieRecyclerAdapter
import com.tsdev.tsandroid.ui.viewholder.MovieRecyclerViewViewHolder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieRecyclerViewViewHolder.OnClickDelegate {

    private val disposable = CompositeDisposable()

    private val movieRecyclerAdapter: MovieRecyclerAdapter by lazy {
        MovieRecyclerAdapter(this)
    }

    private val naverRepository by lazy {
        NaverRepositoryImpl(NaverAPI.movieAPI)
    }

    private fun getMovieList(query: String) {
        disposable.add(
            naverRepository.getMovieList(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieRecyclerAdapter.clear()
                    movieRecyclerAdapter.addItems(it)
                    movieRecyclerAdapter.notifyDataSetChanged()
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_img.setOnClickListener {
            getMovieList(edit_query.text.toString())
        }

        movie_recycler.run {
            adapter = movieRecyclerAdapter
        }
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    override fun onClickEventListener(position: Int) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(movieRecyclerAdapter.itemList[position].link)
            )
        )
    }
}
