package com.world.tree.architecturestudy.view

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.world.tree.architecturestudy.CommonApplication
import com.world.tree.architecturestudy.MovieContainer
import com.world.tree.architecturestudy.R
import com.world.tree.architecturestudy.databinding.ActivityMainBinding
import com.world.tree.architecturestudy.model.Movie
import com.world.tree.architecturestudy.presenter.MovieContract
import com.world.tree.architecturestudy.presenter.MoviePresenterImpl
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MovieContract.View, MovieAdapter.OnItemClickListener {
    private lateinit var movieContainer: MovieContainer
    private lateinit var adapter: MovieAdapter
    private lateinit var presenter: MovieContract.Presenter
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        movieContainer = (application as CommonApplication).movieContainer
        presenter = MoviePresenterImpl( this, movieContainer.repository)
        adapter = MovieAdapter()
        adapter.setOnclickItemListener(this)
        recyclerView.adapter = adapter

        btnSearch.setOnClickListener {
            presenter.searchMovie(etUrl.text.toString())
        }
    }


    override fun showToast(m: String) {
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show()
    }

    override fun setMovieData(list: List<Movie.Item>) {
        adapter.addData(list)
    }

    override fun clearList() {
        adapter.clearData()
    }

    override fun goToLink(link: String) {
        Log.d("Main", "goToLink() called link : $link")
        baseContext.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(link))
                .addFlags(FLAG_ACTIVITY_NEW_TASK)
        )
    }
}
