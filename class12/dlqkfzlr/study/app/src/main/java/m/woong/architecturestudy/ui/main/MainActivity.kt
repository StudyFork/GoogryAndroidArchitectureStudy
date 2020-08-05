package m.woong.architecturestudy.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = (application as MyApplication).appContainer
        viewAdapter = MovieAdapter()
        activityMainBinding =
            DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        movieViewModel = MovieViewModel(appContainer.movieRepository)
        activityMainBinding.viewModel = movieViewModel
        activityMainBinding.movieRv.adapter = viewAdapter
        activityMainBinding.lifecycleOwner = this

        val queryObserver = Observer<String> {
            Log.v("LiveData", "입력:$it")
            if (it.isNullOrEmpty()) {
                Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        movieViewModel.queryData.observe(this, queryObserver)

        movieViewModel.successResponse.observe(this,
            Observer {
                Log.v("MVVM", it.toString())
                viewAdapter.resetData(it)
            }
        )

    }


}
