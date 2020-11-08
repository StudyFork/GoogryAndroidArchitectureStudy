package com.showmiso.architecturestudy

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.showmiso.architecturestudy.data.remote.RemoteDataSourceImpl
import com.showmiso.architecturestudy.data.repository.NaverRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val disposable = CompositeDisposable()
    private val adapter = MovieAdapter()
    private val naverRepositoryImpl = NaverRepositoryImpl(
        RemoteDataSourceImpl()
    )

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
        naverRepositoryImpl.getMovies(query)
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