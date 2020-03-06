package app.ch.study.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import app.ch.study.R
import app.ch.study.core.BaseActivity
import app.ch.study.data.common.EXTRA_URL
import app.ch.study.databinding.ActivityMainBinding
import app.ch.study.ui.detail.DetailActivity
import app.ch.study.ui.main.viewmodel.MainViewModel
import app.ch.study.util.handleError
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val vm: MainViewModel by viewModel()

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
        binding.lifecycleOwner = this

        vm.showError.observe(this, Observer<String> { value ->
            handleError(this, value)
        })
    }

}
