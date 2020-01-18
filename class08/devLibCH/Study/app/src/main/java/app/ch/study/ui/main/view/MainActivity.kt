package app.ch.study.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app.ch.study.R
import app.ch.study.core.BaseActivity
import app.ch.study.data.common.EXTRA_URL
import app.ch.study.data.local.LocalDataManager
import app.ch.study.data.remote.response.MovieModel
import app.ch.study.ui.detail.DetailActivity
import app.ch.study.ui.main.presenter.MainContract
import app.ch.study.ui.main.presenter.MainPresenter

class MainActivity : BaseActivity<MainPresenter>(R.layout.activity_main), MainContract.View {

    override val presenter: MainPresenter by lazy { MainPresenter(this@MainActivity) }

    override var pbLoading: FrameLayout
        get() = findViewById(R.id.pb_loading)
        set(_) {}

    private val adapter: MovieAdapter by lazy {
        MovieAdapter { url ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            startActivity(intent)
        }
    }

    private lateinit var etSearch: EditText
    private lateinit var noResultView: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEvent()

        val name = (LocalDataManager.getInstance(this)?.getQuery()) ?: ""
        if (name.isNotEmpty()) {
            etSearch.setText(name)
            presenter.searchMovie(name)
        }
    }

    private fun initEvent() {
        val btnSearch: Button = findViewById(R.id.btn_search)
        val rvMovie: RecyclerView = findViewById(R.id.rv_movie)
        etSearch = findViewById(R.id.et_search)
        noResultView = findViewById(R.id.no_result_view)

        rvMovie.adapter = adapter

        btnSearch.setOnClickListener {
            val name = etSearch.text.toString()
            presenter.searchMovie(name)
        }
    }

    override fun showError(error: String) = showLongMsg(error)

    override fun showMovieList(items: MutableList<MovieModel>) {
        noResultView.visibility = View.GONE
        adapter.replaceAll(items)
    }

    override fun showErrorEmptyQuery() = showLongMsg(getString(R.string.empty_query))

    override fun showEmptyResult() {
        adapter.clear()
        noResultView.visibility = View.VISIBLE
    }

    private fun showLongMsg(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

}
