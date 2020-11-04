package com.showmiso.architecturestudy

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.showmiso.architecturestudy.api.ApiInterface
import com.showmiso.architecturestudy.api.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val api: ApiInterface by lazy {
        RetrofitClient.createService(
            Constants.MOVIE_URL,
            Constants.CLIENT_ID,
            Constants.CLIENT_SECRET
        )
    }
    private val disposable = CompositeDisposable()
    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    private fun initUi() {
        rcv_result.layoutManager = LinearLayoutManager(this)
        rcv_result.adapter = adapter

        btn_search.setOnClickListener {
            val text = et_search.text.toString()
            updateMovieList(text)
        }
        et_search.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val text = textView.text.toString()
                updateMovieList(text)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun updateMovieList(query: String) {
        if (query.isEmpty()) {
            Toast.makeText(this, getString(R.string.msg_request_text), Toast.LENGTH_SHORT).show()
            return
        }
        api.getMovies(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d(tag, "Success")
                it.items?.also { movieList ->
                    if (movieList.isEmpty()) {
                        Toast.makeText(this, getString(R.string.msg_no_result), Toast.LENGTH_SHORT)
                            .show()
                        return@subscribe
                    }
                    adapter.setMovieList(movieList)
                }
            }, {
                Log.e(tag, "Fail", it)
            })
            .addTo(disposable)
    }

    companion object {
        private val tag = MainActivity::class.java.simpleName
    }
}