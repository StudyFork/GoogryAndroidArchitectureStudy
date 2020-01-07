package com.example.kotlinapplication.ui.base

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.kotlinapplication.databinding.FragmentPageBinding

abstract class BaseBindingFragment(resource: Int) : Fragment() {
    private val layout = resource
    lateinit var binding : FragmentPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout,container,false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    fun toast(message: String) = Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()


    fun webLink(message: String) {
        val uri: Uri = Uri.parse(message)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    fun setEmptyView(isEmpty: Boolean) {
        if (isEmpty) {
            binding.textviewHomeEmpty.visibility = View.VISIBLE
        } else {
            binding.textviewHomeEmpty.visibility = View.GONE
        }
    }


}