package mi.song.class12android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mi.song.class12android.R
import mi.song.class12android.data.model.MovieInfo
import mi.song.class12android.databinding.ActivityMainBinding
import mi.song.class12android.presenter.MovieInterface
import mi.song.class12android.presenter.MoviePresenter
import mi.song.class12android.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity(), MovieInterface.View {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: MovieInterface.Presenter
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        init()
    }

    private fun init() {
        presenter = MoviePresenter(baseContext, this)
        viewModel = MovieViewModel(this@MainActivity)

        initUi()
    }

    private fun initUi() {
        binding.mainView = this@MainActivity
        binding.mainVm = viewModel

        movieAdapter = MovieAdapter()
        binding.listMovie.adapter = movieAdapter
    }

    override fun showMessage(msg: String) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun updateMovieList(list: List<MovieInfo>) {
        movieAdapter.clearMovieList()
        movieAdapter.addMovieInfo(list)
    }

    override fun queryMovie() {
        binding.edtQuery.text?.toString()?.let { query ->
            presenter.requestMovieData(query)
        }
    }
}
