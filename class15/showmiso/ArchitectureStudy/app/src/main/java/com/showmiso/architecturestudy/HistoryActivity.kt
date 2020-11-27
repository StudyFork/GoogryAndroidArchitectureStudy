package com.showmiso.architecturestudy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.showmiso.architecturestudy.data.local.LocalDataSourceImpl
import com.showmiso.architecturestudy.data.remote.RemoteDataSourceImpl
import com.showmiso.architecturestudy.data.repository.NaverRepositoryImpl
import com.showmiso.architecturestudy.databinding.ActivityHistoryBinding
import com.showmiso.architecturestudy.model.HistoryContact
import com.showmiso.architecturestudy.model.HistoryPresenter

class HistoryActivity : AppCompatActivity(), HistoryContact.View,
    HistoryAdapter.OnHistoryClickListener {
    private val presenter by lazy {
        HistoryPresenter(
            view = this,
            naverRepository = run {
                NaverRepositoryImpl(
                    RemoteDataSourceImpl(),
                    LocalDataSourceImpl(this@HistoryActivity)
                )
            }
        )
    }
    private val adapter = HistoryAdapter(this)

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history)
        binding.presenter = presenter
        binding.activity = this

        initUi()
    }

    private fun initUi() {
        adapter.setHistoryList(presenter.getHistory())
        binding.rcvHistory.adapter = adapter
    }

    override fun onItemClick(text: String) {
        val intent = Intent()
        intent.putExtra(Constants.INTENT_KEY_HISTORY, text)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onDeleteItem(text: String) {
        presenter.removeHistory(text)
    }

    override fun removeAllHistory() {
        presenter.removeAllHistory()
        adapter.clearHistoryList()
        Toast.makeText(this, getString(R.string.msg_delete_all), Toast.LENGTH_SHORT).show()
    }
}
