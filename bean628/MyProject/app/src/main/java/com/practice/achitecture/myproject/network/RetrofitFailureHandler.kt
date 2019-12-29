package com.practice.achitecture.myproject.network

import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.practice.achitecture.myproject.makeToast

class EmptyDataException(errorMsgResStringId: Int) : Exception(errorMsgResStringId.toString())
class ResponseNotSuccessException(errorMsgResStringId: Int) :
    Exception(errorMsgResStringId.toString())

fun errorHandler(activity: AppCompatActivity, e: Throwable) {
    val message = e.message.toString()
    if (message.isDigitsOnly()) {
        activity.makeToast(activity.getString(message.toInt()))
    } else {
        activity.makeToast(message)
    }
}