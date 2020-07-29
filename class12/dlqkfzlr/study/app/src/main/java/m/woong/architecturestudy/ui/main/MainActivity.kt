package m.woong.architecturestudy.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import m.woong.architecturestudy.AppContainer
import m.woong.architecturestudy.MovieViewModel
import m.woong.architecturestudy.MyApplication
import m.woong.architecturestudy.R
import m.woong.architecturestudy.databinding.ActivityMainBinding
import m.woong.architecturestudy.ui.adapter.MovieAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var viewAdapter: MovieAdapter
    private lateinit var appContainer: AppContainer
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = (application as MyApplication).appContainer
        viewAdapter = MovieAdapter()
        activityMainBinding =
            DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        val movieViewModel = MovieViewModel(viewAdapter, appContainer.movieRepository)
        activityMainBinding.viewModel = movieViewModel

        movieViewModel.run {
            // TODO: Toast메세지 추가
        }

    }

}
