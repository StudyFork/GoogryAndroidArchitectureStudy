package com.architecture.androidarchitecturestudy.ui.main


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Observable
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.architecture.androidarchitecturestudy.databinding.ActivityMainBinding
import com.architecture.androidarchitecturestudy.ui.base.BaseActivity
import com.architecture.androidarchitecturestudy.ui.searchhistory.SearchHistoryActivity
import com.architecture.androidarchitecturestudy.webservice.ApiClient

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by lazy {
        val remoteDataSourceImpl = MovieRemoteDataSourceImpl(ApiClient.NETWORK_SERVICE)
        val localDataSourceImpl = MovieLocalDataSourceImpl()
        MainViewModel(MovieRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainActivity = this
        binding.viewModel = viewModel
        setViewModelEvent()
    }

    private fun setViewModelEvent() {
        viewModel.onFailedEvent.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@MainActivity, "네트워크 통신 실패.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.onEmptyQuery.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@MainActivity, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.onEmptyResult.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@MainActivity, "관련 검색어의 결과가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun searchHistory() {
        val intent = Intent(this, SearchHistoryActivity::class.java)
        startActivityForResult(intent, REQ_CODE_SEARCH_HISTORY)
    }

    companion object {
        const val REQ_CODE_SEARCH_HISTORY = 101
    }
}