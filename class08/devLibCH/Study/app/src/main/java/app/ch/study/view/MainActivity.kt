package app.ch.study.view

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
import app.ch.study.data.remote.api.WebApiDefine
import app.ch.study.data.remote.api.WebApiTask
import app.ch.study.data.remote.response.MovieModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class MainActivity : AppCompatActivity(), MovieAdapter.EventListener {

    private val compositeDisposable = CompositeDisposable()
    private var adapter: MovieAdapter? = null

    lateinit var rvMovie: RecyclerView
    lateinit var etSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun initEvent() {
        val btnSearch: Button = findViewById(R.id.btn_search)
        rvMovie = findViewById(R.id.rv_movie)
        etSearch = findViewById(R.id.et_search)

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

        val search = WebApiTask.getInstance(this).searchMovie(name)

        addDisposable(
            search
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val list = response.items

                    if (adapter == null) {
                        adapter = MovieAdapter(this, list as ArrayList<MovieModel>, this)
                        rvMovie.adapter = adapter
                    } else {
                        adapter?.replaceAll(list as ArrayList<MovieModel>)
                        adapter?.notifyDataSetChanged()
                    }
                }, {
                    val error = handleError(it)
                    Toast.makeText(this, error, Toast.LENGTH_LONG).show()
                })
        )
    }

    override fun itemClick(url: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_URL, url)
        startActivity(intent)
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
