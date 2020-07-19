package mi.song.class12android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mi.song.class12android.R
import mi.song.class12android.data.model.MovieInfo
import mi.song.class12android.data.repository.SearchMovieRepository
import mi.song.class12android.data.repository.SearchMovieRepositoryImpl
import mi.song.class12android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var searchMovieRepository: SearchMovieRepository
    private lateinit var queryMovie: View.OnClickListener
    private lateinit var binding: ActivityMainBinding

    private fun success(movieList: List<MovieInfo>) {
        movieAdapter.addMovieInfo(movieList)
    }

    private fun fail(t: Throwable) {
        Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        init()
    }

    private fun init() {
        searchMovieRepository = SearchMovieRepositoryImpl(baseContext)

        initUi()
    }

    private fun initUi() {
        queryMovie = View.OnClickListener {
            binding.edtQuery.text?.toString()?.let { query ->
                movieAdapter.clearMovieList()
                searchMovieRepository.getRemoteMovieData(query, success = ::success, fail = ::fail)
            }
        }

        binding.btnSearch.setOnClickListener(queryMovie)

        movieAdapter = MovieAdapter()
        binding.listMovie.adapter = movieAdapter
    }
}
