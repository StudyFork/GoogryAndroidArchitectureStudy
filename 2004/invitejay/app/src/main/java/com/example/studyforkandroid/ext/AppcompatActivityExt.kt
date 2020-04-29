package com.example.studyforkandroid.ext

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.makeToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}