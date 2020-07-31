package mi.song.class12android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import mi.song.class12android.R
import mi.song.class12android.data.model.MovieInfo
import mi.song.class12android.data.repository.SearchMovieRepositoryImpl
import mi.song.class12android.databinding.ActivityMainBinding
import mi.song.class12android.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        init()
    }

    private fun init() {
        viewModel = MovieViewModel(SearchMovieRepositoryImpl(this@MainActivity))

        initUi()
    }

    private fun initUi() {
        binding.mainVm = viewModel

        movieAdapter = MovieAdapter()
        binding.listMovie.adapter = movieAdapter

        viewModel.message.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showMessage(viewModel.message.get())
            }
        })

    }

    fun showMessage(msg: String?) {
        msg?.let {
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        }
    }

}
