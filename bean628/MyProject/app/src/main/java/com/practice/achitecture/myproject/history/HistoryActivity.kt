package com.practice.achitecture.myproject.history

import android.os.Bundle
import androidx.lifecycle.Observer
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.base.BaseNaverSearchActivity
import com.practice.achitecture.myproject.databinding.ActivityHistoryBinding
import org.koin.android.viewmodel.ext.android.getViewModel

class HistoryActivity : BaseNaverSearchActivity<ActivityHistoryBinding>(R.layout.activity_history) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvSearchedList.adapter = searchNaverAdapter
        val historyViewModel: HistoryViewModel = getViewModel()
        historyViewModel.apply {
            eventStringMessageId.observe(this@HistoryActivity, Observer {
                if (it != -999) {
                    showToast(it)
                }
            })

            loadHistory()
        }
        binding.lifecycleOwner = this
        binding.viewModel = historyViewModel

    }

}