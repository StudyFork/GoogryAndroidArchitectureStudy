package com.example.architecture.activity.search

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import com.example.architecture.R
import com.example.architecture.activity.search.adapter.MovieAdapter
import com.example.architecture.data.repository.NaverRepositoryImpl
import com.example.architecture.databinding.ActivitySearchBinding
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val vm = SearchViewModel(NaverRepositoryImpl(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding.vm = vm

        setupRecyclerview()
        setupViewModelEvent()

    }

    private fun setupRecyclerview() {
        recyclerview_search_movieList.adapter = MovieAdapter()
    }

    private fun setupViewModelEvent() {
        vm.showMessageEmptyResult.addOnPropertyChangedCallback(object :
            OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.not_found_result),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        vm.showMessageEmptyKeyword.addOnPropertyChangedCallback(object :
            OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.empty_keyword),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


}
