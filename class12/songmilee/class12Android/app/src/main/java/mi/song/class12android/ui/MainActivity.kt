package mi.song.class12android.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import mi.song.class12android.R
import mi.song.class12android.data.model.MovieInfo
import mi.song.class12android.data.repository.SearchMovieRepository
import mi.song.class12android.data.repository.SearchMovieRepositoryImpl

class MainActivity : AppCompatActivity() {
    lateinit var edtQuery: EditText
    lateinit var btnSearch: Button

    lateinit var listMovie: RecyclerView
    lateinit var movieAdapter: MovieAdapter

    lateinit var searchMovieRepository: SearchMovieRepository

    val queryMovie: View.OnClickListener = View.OnClickListener {
        edtQuery.text?.toString()?.let { query ->
            movieAdapter.clearMovieList()
            searchMovieRepository.getRemoteMovieData(query, success = ::success, fail = ::fail)
        }
    }

    private fun success(movieList: List<MovieInfo>) {
        movieAdapter.addMovieInfo(movieList)
    }

    private fun fail(t: Throwable) {
        Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        searchMovieRepository = SearchMovieRepositoryImpl(baseContext)

        initUi()
    }

    private fun initUi() {
        edtQuery = findViewById(R.id.edt_query)

        btnSearch = findViewById(R.id.btn_search)
        btnSearch.setOnClickListener(queryMovie)

        listMovie = findViewById(R.id.list_movie)
        movieAdapter = MovieAdapter()
        listMovie.adapter = movieAdapter
    }
}
