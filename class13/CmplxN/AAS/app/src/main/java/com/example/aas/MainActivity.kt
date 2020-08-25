package com.example.aas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(R.layout.activity_main) {
	private val compositeDisposable = CompositeDisposable()
	private val movieAdapter = MovieAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		rcv_movie.adapter = movieAdapter

		RxTextView.textChanges(et_movie_name)
			.subscribe { btn_request.isEnabled = it.isNotBlank() }
			.addTo(compositeDisposable)

		btn_request.setOnClickListener {
			val keyword = et_movie_name.text.also { et_movie_name.setText("") }

			et_movie_name.clearFocus()
			hideKeyboard(this, et_movie_name)

			RetrofitManager.naverMoviesApi.getMovies(keyword.toString())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
					{
						Toast.makeText(this, "Search Completed", Toast.LENGTH_SHORT).show()
						movieAdapter.setList(it.movies)
					},
					{
						Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
						it.printStackTrace()
					}
				)
				.addTo(compositeDisposable)
		}
	}

	override fun onDestroy() {
		compositeDisposable.clear()
		super.onDestroy()
	}
}