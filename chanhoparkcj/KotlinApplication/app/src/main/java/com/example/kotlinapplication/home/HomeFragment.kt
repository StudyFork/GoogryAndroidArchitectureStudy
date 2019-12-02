package com.example.kotlinapplication.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import com.example.kotlinapplication.R


class HomeFragment : Fragment() {
    private var mHomeSearchBtn:Button ?=null
    private var mHomeSearchEdit:EditText ?=null
    private var mHomeRecyclerView:RecyclerView ?=null
    private var mHomeTypeBtn:Button ?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHomeSearchBtn = view.findViewById(R.id.home_search_btn)
        mHomeRecyclerView=view.findViewById(R.id.home_recyclerview)
        mHomeSearchEdit=view.findViewById(R.id.home_search_edit)
        mHomeTypeBtn= view.findViewById(R.id.home_type_btn)
        start()
    }

    private fun start(){
        buttonClick()
    }
    private fun buttonClick(){
        mHomeSearchBtn!!.setOnClickListener{
            if(mHomeSearchEdit!!.text.isEmpty()){
                Toast.makeText(context,"검색어를 입력하세요",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"검색어 :"+mHomeSearchEdit!!.text.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}
