package com.olaf.nukeolaf.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.olaf.nukeolaf.R
import com.olaf.nukeolaf.data.local.MovieLocalDataSourceImpl
import com.olaf.nukeolaf.data.remote.MovieRemoteDataSourceImpl
import com.olaf.nukeolaf.data.repository.MovieRepositoryImpl
import com.olaf.nukeolaf.databinding.ActivityMainBinding

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

        binding.vm = viewModel

        viewModel.errorType.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (viewModel.errorType.get()) {
                    1 -> makeToast("검색어를 입력해 주세요")
                    2 -> makeToast("검색 결과가 존재하지 않습니다")
                    3 -> makeToast("[서버 에러] : 서버에 문제가 있습니다")
                    4 -> makeToast("[네트워크 에러] : 인터넷 연결을 확인해 주세요")
                }
                viewModel.errorType.set(0)
            }
        })

    }

    private fun makeToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
