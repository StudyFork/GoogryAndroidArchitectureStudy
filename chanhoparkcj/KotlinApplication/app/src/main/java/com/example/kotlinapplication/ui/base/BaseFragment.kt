package com.example.kotlinapplication.ui.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment(resource: Int) : Fragment() {
    private val layout = resource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layout, container, false)


    fun toast(message: String) =Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()


    fun webLink(message: String) {
        val uri: Uri = Uri.parse(message)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}