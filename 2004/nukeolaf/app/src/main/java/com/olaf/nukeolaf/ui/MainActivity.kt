package com.olaf.nukeolaf.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.olaf.nukeolaf.R
import com.olaf.nukeolaf.data.local.MovieLocalDataSourceImpl
import com.olaf.nukeolaf.data.remote.MovieRemoteDataSourceImpl
import com.olaf.nukeolaf.data.repository.MovieRepositoryImpl
import com.olaf.nukeolaf.databinding.ActivityMainBinding

const val NO_ERROR = 0
const val EMPTY_SEARCH_WORD = 1
const val NO_QUERY_RESULT = 2
const val SERVER_ERROR = 3
const val NETWORK_ERROR = 4

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        viewModel = MainViewModel(
            MovieRepositoryImpl(
                MovieLocalDataSourceImpl(applicationContext),
                MovieRemoteDataSourceImpl()
            )
        )

        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }

        viewModel.errorType.observe(
            this,
            Observer { errorType ->
                when (errorType) {
                    EMPTY_SEARCH_WORD -> makeToast("검색어를 입력해 주세요")
                    NO_QUERY_RESULT -> makeToast("검색 결과가 존재하지 않습니다")
                    SERVER_ERROR -> makeToast("[서버 에러] : 서버에 문제가 있습니다")
                    NETWORK_ERROR -> makeToast("[네트워크 에러] : 인터넷 연결을 확인해 주세요")
                }
                viewModel.errorType.value = NO_ERROR
            }
        )

    }

    private fun makeToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
