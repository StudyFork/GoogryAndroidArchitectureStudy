package com.showmiso.architecturestudy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
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
        binding.rcvHistory.adapter = adapter
        presenter.initSearchHistory()
    }

    fun removeAllHistory() {
        presenter.removeAllHistory()
    }

    override fun showNoHistory() {
        binding.rcvHistory.visibility = View.GONE
        Toast.makeText(this, getString(R.string.label_no_history), Toast.LENGTH_SHORT).show()
    }

    override fun updateHistoryList(list: List<String>?) {
        binding.tvNoHistory.visibility = View.GONE
        adapter.setHistoryList(list)
    }

    override fun showRemoveAllHistory() {
        adapter.clearHistoryList()
        Toast.makeText(this, getString(R.string.msg_delete_all), Toast.LENGTH_SHORT).show()
    }

    override fun onHistoryItemClick(text: String) {
        val intent = Intent()
        intent.putExtra(Constants.INTENT_KEY_HISTORY, text)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onHistoryItemClickToDelete(text: String) {
        presenter.removeHistory(text)
    }
}
