package study.architecture.myarchitecture.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.base.BaseActivity
import study.architecture.myarchitecture.data.Injection
import study.architecture.myarchitecture.databinding.ActivityMainBinding
import study.architecture.myarchitecture.util.Filter

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModelFactory by lazy {
        MainViewModelFactory(Injection.provideFolderRepository(this))
    }

    private val mainViewModel by lazy {
        ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
    }

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initViewPager()
        initAdapterCallback()
    }

    private fun initViewModel() {
        binding.mainModel = mainViewModel
        binding.lifecycleOwner = this
    }

    private fun initViewPager() {
        binding.vpMain.adapter = mainAdapter
    }

    @Suppress("UNCHECKED_CAST")
    private fun initAdapterCallback() {

        mainViewModel.selectField.observe(this, Observer {
            val (filter, selected) = it

            val order = if (selected) {
                Filter.ASC
            } else {
                Filter.DESC
            }

            for (i in 0 until (mainAdapter.count)) {
                mainAdapter.getFragment(i)?.let { fragment ->
                    fragment.get()?.showTickerListOrderByField(filter, order)
                }
            }
        })
    }
}
