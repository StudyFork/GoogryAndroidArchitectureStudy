package study.architecture.myarchitecture.ui.main

import android.os.Bundle
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.base.BaseActivity
import study.architecture.myarchitecture.data.Injection
import study.architecture.myarchitecture.databinding.ActivityMainBinding
import study.architecture.myarchitecture.util.Filter

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var mainViewModel: MainViewModel

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel =
            MainViewModel(
                Injection.provideFolderRepository(this),
                object : MainViewModel.SortListener {
                    override fun sortTicker(field: Filter.SelectArrow, order: Int) {
                        for (i in 0 until (mainAdapter.count)) {
                            mainAdapter.getFragment(i)?.run {
                                this.get()?.showTickerListOrderByField(field, order)
                            }
                        }
                    }
                }
            ).apply {
                binding.mainModel = this
            }

        initViewPager()

        mainViewModel.loadData()

    }

    override fun onDestroy() {
        mainViewModel.destroyView()
        super.onDestroy()
    }

    private fun initViewPager() {
        binding.vpMain.adapter = mainAdapter
    }
}
