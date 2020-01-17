package app.ch.study.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import app.ch.study.R
import app.ch.study.data.common.EXTRA_URL
import app.ch.study.data.common.PREF_NAME
import app.ch.study.data.local.LocalDataManager
import app.ch.study.data.local.source.NaverQueryLocalDataSourceImpl
import app.ch.study.data.remote.api.WebApiDefine
import app.ch.study.data.remote.api.WebApiTask
import app.ch.study.data.remote.source.NaverQueryRemoteDataSourceImpl
import app.ch.study.data.repository.NaverQueryRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var adapter: MovieAdapter

    private lateinit var rvMovie: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var repository: NaverQueryRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEvent()

        val prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val query = (LocalDataManager.getInstance(prefs).getQuery())
        if(query.isNotEmpty()) {
            etSearch.setText(query)
            searchMovie(query)
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initEvent() {
        val btnSearch: Button = findViewById(R.id.btn_search)
        rvMovie = findViewById(R.id.rv_movie)
        etSearch = findViewById(R.id.et_search)

        adapter = MovieAdapter {
                url ->

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            startActivity(intent)
        }

        rvMovie.adapter = adapter

        val prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val local = NaverQueryLocalDataSourceImpl(LocalDataManager.getInstance(prefs))
        val remote = NaverQueryRemoteDataSourceImpl(WebApiTask.getInstance())
        repository = NaverQueryRepositoryImpl(local, remote)

        btnSearch.setOnClickListener {
            val name = etSearch.text.toString()
            searchMovie(name)
        }
    }

    private fun searchMovie(name: String) {
        if (name.isEmpty()) {
            Toast.makeText(this, "검색어를 입력하세요.", Toast.LENGTH_LONG).show()
            return
        }

        val search = repository.searchMovie(name)

        addDisposable(
            search
                .subscribe({ response ->
                    val list = response.items
                    adapter.replaceAll(list)
                }, {
                    val error = handleError(it)
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                })
        )
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun handleError(e: Throwable): String {
        if (e is HttpException) {
            val code = e.code()
            WebApiDefine.showErrorLog(code)
        } else {
            Log.e("HttpErrorHandler", e.toString())
        }

        return ((e as? Exception)?.message) ?: ""
    }
}
