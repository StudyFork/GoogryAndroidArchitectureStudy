package com.example.androidarchitecture.common

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

@SuppressLint("ShowToast")
fun Context.toast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT)
}