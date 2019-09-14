package study.architecture.myarchitecture.ui.main

import android.os.Bundle
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.base.BaseActivity
import study.architecture.myarchitecture.data.Injection
import study.architecture.myarchitecture.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel =
            MainViewModel(
                Injection.provideFolderRepository(this),
                MainAdapter(supportFragmentManager)
            ).apply {
                binding.mainModel = this
            }

        mainViewModel.loadData()

    }

    override fun onDestroy() {
        mainViewModel.detachView()
        super.onDestroy()
    }
}
