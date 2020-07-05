package mi.song.class12android.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import mi.song.class12android.R
import mi.song.class12android.data.model.MovieResponse
import mi.song.class12android.data.source.remote.MovieService
import mi.song.class12android.data.source.remote.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var edtQuery: EditText
    lateinit var btnSearch: Button
    lateinit var listMovie: RecyclerView

    lateinit var movieAdapter: MovieAdapter

    lateinit var movieService: MovieService

    val queryMovie: View.OnClickListener = View.OnClickListener {
        edtQuery.text?.toString()?.let { query ->
            movieAdapter.clearMovieList()

            movieService.getMovieInfo(query).enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Toast.makeText(baseContext, "Sorry, try it next time", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.items?.apply {
                            movieAdapter.addMovieInfo(this)
                        }
                    }
                }

            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init() {
        movieService = RetrofitHelper.getService(baseContext)

        initUi()
    }

    fun initUi() {
        edtQuery = findViewById(R.id.edt_query)

        btnSearch = findViewById(R.id.btn_search)
        btnSearch.setOnClickListener(queryMovie)

        listMovie = findViewById(R.id.list_movie)
        movieAdapter = MovieAdapter()
        listMovie.adapter = movieAdapter
    }
}
