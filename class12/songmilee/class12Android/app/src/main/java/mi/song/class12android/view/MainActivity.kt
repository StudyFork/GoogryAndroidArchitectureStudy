package mi.song.class12android.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonObject
import mi.song.class12android.R
import mi.song.class12android.model.data.MovieInfo
import mi.song.class12android.network.MovieService
import mi.song.class12android.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var edtQuery:EditText? = null
    var btnSearch:Button? = null
    var listMovie:RecyclerView? = null

    var movieAdapter:MovieAdapter? = null

    lateinit var movieService:MovieService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init(){
        movieService = RetrofitHelper.getService(baseContext)

        initUi()
    }

    fun initUi(){
        edtQuery = findViewById(R.id.edt_query)

        btnSearch = findViewById(R.id.btn_search)
        btnSearch?.setOnClickListener(queryMovie)

        listMovie = findViewById(R.id.list_movie)
        movieAdapter = MovieAdapter()
        listMovie?.adapter = movieAdapter
    }
    
    val queryMovie: View.OnClickListener = View.OnClickListener {
        edtQuery?.text?.toString()?.let { query ->
            movieAdapter?.resetMovieList()

            movieService.getMovieInfo(query).enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(baseContext, "Sorry, try it next time", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if(response.isSuccessful){
                        val jsonBody = response.body()
                        jsonBody?.getAsJsonArray("items")?.let{ items ->
                            for(movie in items){
                                val resultInfo = Gson().fromJson(movie.asJsonObject, MovieInfo::class.java)
                                movieAdapter?.addMovieInfo(resultInfo)
                            }
                        }
                    }
                }

            })
        }
    }
}
