package com.example.architecture.activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.architecture.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setRecyclerview()
    }

    private fun setRecyclerview() {
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        // * Test
        val movies = arrayListOf(
                MovieVO("https://search.pstatic.net/common?type=o&size=120x171&quality=100&direct=true&src=https%3A%2F%2Fs.pstatic.net%2Fmovie.phinf%2F20181019_236%2F1539926790655oHv5z_JPEG%2Fmovie_image.jpg"
                        , "해리포터", "2001", "https://", 5.0f),
                MovieVO("https://movie-phinf.pstatic.net/20170112_293/1484183486221oM8s1_JPEG/movie_image.jpg"
                        , "반지의 제왕", "2001", "https://", 4.0f)
        )

        recyclerview_search_movieList.adapter = MovieListAdapter(movies)
        recyclerview_search_movieList.layoutManager = gridLayoutManager
    }


}
