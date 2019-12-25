package com.practice.achitecture.myproject

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.makeToast(resId: Int) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.makeToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.openActivity(cls: Class<*>) {
    val intent = Intent(this, cls)
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    startActivity(intent)
}