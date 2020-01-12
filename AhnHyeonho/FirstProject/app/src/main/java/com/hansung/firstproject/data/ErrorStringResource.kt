package com.hansung.firstproject.data

import androidx.annotation.StringRes
import com.hansung.firstproject.R

enum class ErrorStringResource(@StringRes val resId: Int) {
    INTERNET_ERROR(R.string.internet_error_message),
    EMPTY_KEWORD_ERROR(R.string.empty_keyword_message),
    EMPTY_LIST_ERROR(R.string.empty_list_message),
    ERROR_CODE_400(R.string.error_message_400),
    ERROR_CODE_401(R.string.error_message_401),
    ERROR_CODE_500(R.string.error_message_500)
}