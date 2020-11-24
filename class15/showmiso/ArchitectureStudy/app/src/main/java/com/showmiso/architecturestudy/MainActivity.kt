package com.showmiso.architecturestudy

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.showmiso.architecturestudy.api.MovieModel
import com.showmiso.architecturestudy.data.remote.RemoteDataSourceImpl
import com.showmiso.architecturestudy.data.repository.NaverRepositoryImpl
import com.showmiso.architecturestudy.databinding.ActivityMainBinding
import com.showmiso.architecturestudy.model.MovieContract
import com.showmiso.architecturestudy.model.MoviePresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieContract.View {
    private val adapter = MovieAdapter()
    private val presenter = MoviePresenter(
        view = this,
        naverRepository = run {
            NaverRepositoryImpl(
                RemoteDataSourceImpl()
            )
        }
    )
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

    override fun onDestroy() {
        presenter.clearObservable()
        super.onDestroy()
    }

    private fun updateMovieList(query: String) {
        presenter.getMovies(query)
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
        imm.hideSoftInputFromWindow(et_search.windowToken, 0)
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    companion object {
        private val tag = MainActivity::class.java.simpleName
    }

}
