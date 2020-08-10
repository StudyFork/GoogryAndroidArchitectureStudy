package m.woong.architecturestudy.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import m.woong.architecturestudy.MovieViewModel
import m.woong.architecturestudy.R
import m.woong.architecturestudy.databinding.ActivityMainBinding
import m.woong.architecturestudy.ui.adapter.MovieAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewAdapter: MovieAdapter by inject()
    private val movieViewModel: MovieViewModel by viewModel()       //viewModel은 Koin, viewModels는 viewModel ktx임

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding =
            DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        activityMainBinding.viewModel = movieViewModel
        activityMainBinding.movieRv.adapter = viewAdapter
        activityMainBinding.lifecycleOwner = this

        movieViewModel.queryNullorEmpty.observe(this,
            Observer<Boolean> {
                Log.v("LiveData", "입력없음:$it")
                if (it) {
                    Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
                    movieViewModel.queryNullorEmpty.value = false
                }
            }
        )

        movieViewModel.successResponse.observe(this,
            Observer {
                Log.v("MVVM", it.toString())
                viewAdapter.resetData(it)
            }
        )

    }


}
