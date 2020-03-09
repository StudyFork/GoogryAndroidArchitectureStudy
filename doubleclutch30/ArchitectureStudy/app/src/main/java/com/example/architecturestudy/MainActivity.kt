package com.example.architecturestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.architecturestudy.databinding.ActivityMainBinding
import com.example.architecturestudy.ui.MainContract
import com.example.architecturestudy.ui.MainPresenter
import com.example.architecturestudy.ui.blog.BlogFragment
import com.example.architecturestudy.ui.image.ImageFragment
import com.example.architecturestudy.ui.kin.KinFragment
import com.example.architecturestudy.ui.movie.MovieFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding

    private val presenter : MainContract.Presenter by lazy {
        MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        presenter.start()
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.bottom_nav_movie -> {
                println("Movie")
                showFragment(MovieFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_blog -> {
                println("Blog   ")
                showFragment(BlogFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_kin -> {
                println("Kin")
                showFragment(KinFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_nav_image -> {
                print("Image")
                showFragment(ImageFragment())
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}
