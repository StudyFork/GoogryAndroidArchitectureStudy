package com.hjhan.hyejeong.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hjhan.hyejeong.R
import com.hjhan.hyejeong.data.repository.NaverRepositoryImpl
import com.hjhan.hyejeong.data.source.local.NaverLocalDataSourceImpl
import com.hjhan.hyejeong.data.source.remote.NaverRemoteDataSourceImpl
import com.hjhan.hyejeong.databinding.ActivityMainBinding
import com.hjhan.hyejeong.ui.QueryHistoryDialog.Companion.HISTORY_DIALOG_TAG
import com.hjhan.hyejeong.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val remoteDataSourceImpl = NaverRemoteDataSourceImpl()
                val localDataSourceImpl = NaverLocalDataSourceImpl()
                return MainViewModel(NaverRepositoryImpl(remoteDataSourceImpl, localDataSourceImpl)) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setViewModelEvent()
    }

    private fun setViewModelEvent() {
        viewModel.onFailedEvent.observe(this, Observer {
            Toast.makeText(this@MainActivity, "네트워크 통신 실패.", Toast.LENGTH_SHORT).show()
        })

        viewModel.onEmptyQuery.observe(this, Observer {
            Toast.makeText(this@MainActivity, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
        })
    }

    fun onHistoryButtonClicked() {
        //최근 검색 목록
        QueryHistoryDialog().show(supportFragmentManager, HISTORY_DIALOG_TAG)
    }

}
