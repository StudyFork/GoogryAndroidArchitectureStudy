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
import mi.song.class12android.presenter.MovieInterface
import mi.song.class12android.presenter.MoviePresenter

class MainActivity : AppCompatActivity(), MovieInterface.View {
    private lateinit var edtQuery: EditText
    private lateinit var btnSearch: Button

    private lateinit var listMovie: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var queryMovie: View.OnClickListener

    private lateinit var presenter: MovieInterface.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        presenter =
            MoviePresenter(baseContext, this)

        initUi()
    }

    private fun initUi() {
        queryMovie = View.OnClickListener {
            edtQuery.text?.toString()?.let { query ->
                presenter.requestMovieData(query)
            }
        }

        edtQuery = findViewById(R.id.edt_query)

        btnSearch = findViewById(R.id.btn_search)
        btnSearch.setOnClickListener(queryMovie)

        listMovie = findViewById(R.id.list_movie)
        movieAdapter = MovieAdapter()
        listMovie.adapter = movieAdapter
    }

    override fun showMessage(msg: String) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun updateMovieList(list: List<MovieInfo>) {
        movieAdapter.clearMovieList()
        movieAdapter.addMovieInfo(list)
    }
}
