package com.showmiso.architecturestudy.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.showmiso.architecturestudy.Constants
import com.showmiso.architecturestudy.R
import com.showmiso.architecturestudy.api.MovieModel
import com.showmiso.architecturestudy.data.local.LocalDataSourceImpl
import com.showmiso.architecturestudy.data.remote.RemoteDataSourceImpl
import com.showmiso.architecturestudy.data.repository.NaverRepositoryImpl
import com.showmiso.architecturestudy.databinding.ActivityMainBinding
import com.showmiso.architecturestudy.model.MovieContract
import com.showmiso.architecturestudy.model.MoviePresenter

class MainActivity : AppCompatActivity(), MovieContract.View {
    private val adapter = MovieAdapter()
    private val presenter by lazy {
        MoviePresenter(
            view = this,
            naverRepository = run {
                val prefs = getSharedPreferences(Constants.PREF_HISTORY_KEY, Context.MODE_PRIVATE)
                val localDataSourceImpl = LocalDataSourceImpl(prefs)
                val remoteDataSourceImpl = RemoteDataSourceImpl()
                NaverRepositoryImpl(
                    remoteDataSource = remoteDataSourceImpl,
                    localDataSource = localDataSourceImpl
                )
            }
        )
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.presenter = presenter
        binding.activity = this

        initUi()
    }

    private fun initUi() {
        binding.rcvResult.adapter = adapter
    }

    fun onClickHistory(view: View) {
        startActivityForResult(
            Intent(this@MainActivity, HistoryActivity::class.java),
            REQUEST_CODE_HISTORY
        )
    }

    override fun onDestroy() {
        presenter.clearObservable()
        super.onDestroy()
    }

    override fun showEmptyQuery() {
        Toast.makeText(this, getString(R.string.msg_request_text), Toast.LENGTH_SHORT).show()
    }

    override fun showNoMovieResult() {
        Toast.makeText(this, getString(R.string.msg_no_result), Toast.LENGTH_SHORT).show()
    }

    override fun updateMovieList(list: List<MovieModel.Movie>) {
        adapter.setMovieList(list)
    }

    override fun throwError(it: Throwable) {
        Log.e(tag, "Failed", it)
    }

    override fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_HISTORY) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra(Constants.INTENT_KEY_HISTORY)
                result?.let {
                    presenter.getMovies(it)
                    binding.etSearch.setText(it)
                }
            }
        }
    }

    companion object {
        private val tag = MainActivity::class.java.simpleName
        private const val REQUEST_CODE_HISTORY = 0x01
    }
}
