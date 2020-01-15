package com.onit.googlearchitecturestudy.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.onit.googlearchitecturestudy.Movie
import com.onit.googlearchitecturestudy.R
import com.onit.googlearchitecturestudy.databinding.ActivityMainBinding
import com.onit.googlearchitecturestudy.ui.movieInformation.MovieInformationActivity
import com.onit.googlearchitecturestudy.ui.movieInformation.MovieInformationActivity.Companion.MOVIE_URL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var resultMovieListRecyclerAdapter: ResultMovieListRecyclerAdapter
    private lateinit var presenter: MainContract.Presenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)!!
        presenter = MainPresenter(this)
        binding.presenter = presenter
        init()
    }

    override fun hideKeyBoard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    override fun showLoadingProgressBar() {
        loadingProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingProgressBar() {
        loadingProgressBar.visibility = View.GONE
    }

    override fun showToastMessage(message: String, option: Int) {
        Toast.makeText(applicationContext, message, option).show()
    }

    override fun setMovieList(movieList: List<Movie>) {
        resultMovieListRecyclerAdapter.setMovieList(movieList)
    }

    private fun init() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        resultMovieListRecyclerAdapter =
            ResultMovieListRecyclerAdapter { position ->
                clickMovieItem(
                    position
                )
            }
        resultMovieListRecyclerView.adapter = resultMovieListRecyclerAdapter
    }

    private fun clickMovieItem(position: Int) {
        val intent = Intent(this, MovieInformationActivity::class.java).apply {
            putExtra(MOVIE_URL, resultMovieListRecyclerAdapter.getMovieURL(position))
        }
        startActivity(intent)
    }
}
