package com.example.studyapplication.ui.main

import androidx.annotation.IdRes
import com.example.studyapplication.R
import com.example.studyapplication.ui.main.blog.BlogFragment
import com.example.studyapplication.ui.main.image.ImageFragment
import com.example.studyapplication.ui.main.kin.KinFragment
import com.example.studyapplication.ui.main.movie.MovieFragment

class MainPresenter(val view : MainContract.View) : MainContract.Presenter {
    override fun init() {
        view.showSearchFragment(MovieFragment())
    }

    override fun clickTab(@IdRes id : Int) {
        when(id) {
            R.id.tabMovie -> view.showSearchFragment(MovieFragment())
            R.id.tabImage -> view.showSearchFragment(ImageFragment())
            R.id.tabBlog -> view.showSearchFragment(BlogFragment())
            R.id.tabKin -> view.showSearchFragment(KinFragment())
        }
    }

}