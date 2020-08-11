package mi.song.class12android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import mi.song.class12android.R
import mi.song.class12android.databinding.ActivityMainBinding
import mi.song.class12android.viewmodel.MovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.lifecycleOwner = this

        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        binding.mainVm = viewModel

        movieAdapter = MovieAdapter()
        binding.listMovie.adapter = movieAdapter

        viewModel.message.observe(this, Observer {
            showMessage(it)
        })
    }

    fun showMessage(msg: String?) {
        msg?.let {
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        }
    }

}
