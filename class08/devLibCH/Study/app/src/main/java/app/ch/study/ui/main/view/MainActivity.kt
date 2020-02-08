package app.ch.study.ui.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import app.ch.study.R
import app.ch.study.core.BaseActivity
import app.ch.study.data.common.EXTRA_URL
import app.ch.study.data.common.PREF_NAME
import app.ch.study.data.local.LocalDataManager
import app.ch.study.data.remote.response.MovieModel
import app.ch.study.databinding.ActivityMainBinding
import app.ch.study.ui.detail.DetailActivity
import app.ch.study.ui.main.presenter.MainContract
import app.ch.study.ui.main.presenter.MainPresenter

class MainActivity : BaseActivity<ActivityMainBinding, MainPresenter>(R.layout.activity_main),
    MainContract.View {

    override fun getLayoutResId(): Int = R.layout.activity_main

    override val presenter: MainPresenter by lazy {
        val prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val localDataManager = LocalDataManager.getInstance(prefs)
        MainPresenter(this@MainActivity, localDataManager)
    }

    override val pbLoading: FrameLayout by lazy {
        binding.pbLoading
    }

    private val adapter: MovieAdapter by lazy {
        MovieAdapter { url ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.presenter = presenter
        binding.rvMovie.adapter = adapter
    }

    override fun showMovieList(items: MutableList<MovieModel>) = adapter.replaceAll(items)

    override fun showError(error: String) = showLongMsg(error)

    override fun showErrorEmptyQuery() = showLongMsg(getString(R.string.empty_query))

    override fun showEmptyResult() = adapter.clear()

    private fun showLongMsg(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

}
