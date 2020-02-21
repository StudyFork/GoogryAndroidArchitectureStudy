package app.ch.study.ui.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import app.ch.study.R
import app.ch.study.core.BaseActivity
import app.ch.study.data.common.EXTRA_URL
import app.ch.study.data.common.PREF_NAME
import app.ch.study.data.local.LocalDataManager
import app.ch.study.databinding.ActivityMainBinding
import app.ch.study.ext.addOnPropertyChanged
import app.ch.study.ui.detail.DetailActivity
import app.ch.study.ui.main.viewmodel.MainViewModel
import app.ch.study.util.handleError

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val vm: MainViewModel by lazy {
        val prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val localDataManager = LocalDataManager.getInstance(prefs)
        MainViewModel(localDataManager)
    }

    override val pbLoading: FrameLayout by lazy {
        binding.pbLoading
    }

    private val adapter: MovieAdapter by lazy {
        MovieAdapter { url ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvMovie.adapter = adapter

        vm.showError.addOnPropertyChanged { value ->
            handleError(this, value.get())
        }
    }

}
