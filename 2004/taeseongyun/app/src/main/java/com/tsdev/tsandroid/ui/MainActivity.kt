package com.tsdev.tsandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.tsdev.tsandroid.NaverAPI
import com.tsdev.tsandroid.R
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

    private fun getMovieList(disposable: CompositeDisposable, query: String) {
        disposable.add(NaverAPI.movieAPI.getSearchMovie(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                movieRecyclerAdapter.addItems(it.items)
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
//            Log.d("MOVE", "click")
            getMovieList(disposable, edit_query.text.toString())
        }

        movie_recycler.run {
            adapter = movieRecyclerAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    override fun onClickEventListener(position: Int) {
       Log.d("MOVIE", movieRecyclerAdapter.movieList[position].link)
    }
}
