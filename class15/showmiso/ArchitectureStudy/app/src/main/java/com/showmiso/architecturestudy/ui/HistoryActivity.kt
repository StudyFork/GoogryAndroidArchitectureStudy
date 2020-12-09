package com.showmiso.architecturestudy.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.showmiso.architecturestudy.Constants
import com.showmiso.architecturestudy.R
import com.showmiso.architecturestudy.data.local.LocalDataSourceImpl
import com.showmiso.architecturestudy.data.remote.RemoteDataSourceImpl
import com.showmiso.architecturestudy.data.repository.NaverRepositoryImpl
import com.showmiso.architecturestudy.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity(), HistoryAdapter.OnHistoryClickListener {
    private lateinit var binding: ActivityHistoryBinding
    private val historyViewModel by lazy {
        HistoryViewModel(
            naverRepository = run {
                val prefs = getSharedPreferences(Constants.PREF_HISTORY_KEY, Context.MODE_PRIVATE)
                val localDataSourceImpl = LocalDataSourceImpl(prefs)
                val remoteDataSourceImpl = RemoteDataSourceImpl()
                NaverRepositoryImpl(
                    remoteDataSource = remoteDataSourceImpl,
                    localDataSource = localDataSourceImpl
                )
            }
        )
    }
    private val adapter = HistoryAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history)
        binding.vm = historyViewModel

        initUi()
    }

    private fun initUi() {
        binding.rcvHistory.adapter = adapter
        historyViewModel.updateHistoryList()

        historyViewModel.historyList.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                adapter.setHistoryList(historyViewModel.historyList.get())
            }
        })

        historyViewModel.showNoHistory.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@HistoryActivity, getString(R.string.label_no_history), Toast.LENGTH_SHORT).show()
            }
        })

        historyViewModel.showRemoveAllHistory.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                adapter.clearHistoryList()
                Toast.makeText(this@HistoryActivity, getString(R.string.msg_delete_all), Toast.LENGTH_SHORT).show()
            }
        })

        historyViewModel.backPressedEvent.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                onBackPressed()
            }
        })
    }
    
    override fun onHistoryItemClick(text: String) {
        val intent = Intent()
        intent.putExtra(Constants.INTENT_KEY_HISTORY, text)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onHistoryItemClickToDelete(text: String) {
        historyViewModel.removeHistory(text)
    }
}
