package app.ch.study.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.ch.study.R
import app.ch.study.core.BaseActivity
import app.ch.study.data.db.entitiy.MovieModel
import app.ch.study.databinding.ActivityMainBinding
import app.ch.study.viewmodel.MovieViewModel
import app.ch.study.viewmodel.MovieViewModelFactory
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>(), MovieAdapter.EventListener {

    private val movieViewModelFactory: MovieViewModelFactory by inject()

    private var adapter: MovieAdapter? = null

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.view = this
        binding.lifecycleOwner = this

        initEvent()
    }

    private fun initEvent() {
        val movieViewModel = ViewModelProviders.of(this, movieViewModelFactory).get(
            MovieViewModel::class.java)

        binding.viewModel = movieViewModel

        movieViewModel.error.observe(this, Observer {
            error -> Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        })

        movieViewModel.movieList.observe(this, Observer {
            list ->

            if(adapter == null) {
                adapter = MovieAdapter(this, list as ArrayList<MovieModel>, this)
                binding.rvMovie?.adapter = adapter
            } else {
                adapter?.replaceAll(list as ArrayList<MovieModel>)
                adapter?.notifyDataSetChanged()
            }
        })
    }

    fun searchMovie() {
        val name = binding.etSearch.text.toString()
        binding.viewModel?.searchMovie(name)
    }

    override fun itemClick(url: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}
