package com.namjackson.archstudy.base.component

import android.view.View
import android.widget.AdapterView

interface BaseOnItemSelectedListener : AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>, p1: View?, position: Int, p3: Long) {
    }
}