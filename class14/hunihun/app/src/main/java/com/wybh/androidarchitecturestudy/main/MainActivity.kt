package com.wybh.androidarchitecturestudy.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.wybh.androidarchitecturestudy.R
import com.wybh.androidarchitecturestudy.adapter.CinemaAdapter
import com.wybh.androidarchitecturestudy.base.BaseActivity
import com.wybh.androidarchitecturestudy.databinding.ActivityMainBinding
import com.wybh.androidarchitecturestudy.history.RecentSearchWordActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val vm : MainViewModel by viewModels()

    private val cinemaAdapter: CinemaAdapter =
        CinemaAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        initObserve()
    }

    private fun initObserve() {
        vm.run {
            error.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }

            cinemaItemList.observe(this@MainActivity) {
                cinemaAdapter.dataClearAndSetting(it)

                // 키보드 내리기
                imm.hideSoftInputFromWindow(binding.etSearchWord.windowToken, 0)
            }

            recentSearch.observe(this@MainActivity) {
                startActivityForResult(
                    Intent(this@MainActivity, RecentSearchWordActivity::class.java),
                    REQUEST_HISTORY_WORD
                )
            }
        }
    }

    private fun initAdapter() {
        binding.rvCinema.adapter = cinemaAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode) {
            REQUEST_HISTORY_WORD -> {
                binding.etSearchWord.setText(data?.getStringExtra("SEARCH_WORD")!!)
                vm.searchCinema()
            }
        }

    }

    companion object {
        const val REQUEST_HISTORY_WORD = 200
    }
}
