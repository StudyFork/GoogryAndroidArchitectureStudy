package m.woong.architecturestudy.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import m.woong.architecturestudy.AppContainer
import m.woong.architecturestudy.MovieViewModel
import m.woong.architecturestudy.MyApplication
import m.woong.architecturestudy.R
import m.woong.architecturestudy.data.source.remote.model.MovieResponse
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

        with(movieViewModel) {
            this.queryData.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (queryData.get() != null) {
                        Toast.makeText(this@MainActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            this.successResponse.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (successResponse.get() != null) {
                        val list = successResponse.get() as List<MovieResponse.Item>
                        viewAdapter.resetData(list)
                    }
                }
            })

            this.toastMsg.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    Toast.makeText(this@MainActivity, "검색결과를 가져오는데 실패했습니다.", Toast.LENGTH_SHORT)
                        .show()

                }
            })
        }


    }


}
