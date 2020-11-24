package com.showmiso.architecturestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.showmiso.architecturestudy.data.local.LocalDataSourceImpl
import com.showmiso.architecturestudy.data.remote.RemoteDataSourceImpl
import com.showmiso.architecturestudy.data.repository.NaverRepositoryImpl
import com.showmiso.architecturestudy.databinding.ActivityHistoryBinding
import com.showmiso.architecturestudy.model.HistoryContact
import com.showmiso.architecturestudy.model.HistoryPresenter
import com.showmiso.architecturestudy.model.MovieContract
import com.showmiso.architecturestudy.model.MoviePresenter

class HistoryActivity : AppCompatActivity(), HistoryContact.View {
    private val presenter = HistoryPresenter(
        view = this,
        naverRepository = run {
            NaverRepositoryImpl(
                RemoteDataSourceImpl(),
                LocalDataSourceImpl(this@HistoryActivity)
            )
        }
    )
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history)
        binding.presenter = presenter
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.iv_back -> {
                onBackPressed()
            }
        }
    }


}
