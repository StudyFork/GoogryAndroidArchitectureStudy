package com.example.kotlinapplication.ui.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlinapplication.data.model.BlogItem
import com.example.kotlinapplication.data.model.ImageItem
import com.example.kotlinapplication.data.model.KinItem
import com.example.kotlinapplication.data.model.MovieItem
import kotlinx.android.synthetic.main.fragment_page.*

abstract class BaseFragment(resource: Int) : Fragment() {
    private val layout = resource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layout, container, false)

    fun toast(message: String) = Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()


    fun webLink(message: String) {
        val uri: Uri = Uri.parse(message)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun setEmptyView(isEmpty: Boolean) {
        if (isEmpty) {
            textview_home_empty.visibility = View.VISIBLE
        } else {
            textview_home_empty.visibility = View.GONE
        }
    }

    fun onBlogItemClick(blogItems: BlogItem) {
        toast(blogItems.link)
        webLink(blogItems.link)
    }

    fun onMovieItemClick(movieItems: MovieItem) {
        toast(movieItems.link)
        webLink(movieItems.link)
    }

    fun onImageItemClick(imageItems: ImageItem) {
        toast(imageItems.link)
        webLink(imageItems.link)
    }

    fun onKinItemClick(kinItems: KinItem) {
        toast(kinItems.link)
        webLink(kinItems.link)
    }

}