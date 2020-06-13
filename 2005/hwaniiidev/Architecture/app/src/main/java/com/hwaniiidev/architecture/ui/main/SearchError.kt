package com.hwaniiidev.architecture.ui.main

import com.hwaniiidev.architecture.R

enum class SearchError(val errorMessage:String){
    INIT("하는 검색어를 입력해주세요."),
    NONE_ERROR(""),
    RESPONSE_IS_NONE("검색결과가 없습니다.\n다른 검색어을 입력해주세요."),
    RESPONSE_ERROR("다시 시도해주세요."),
    NETWORK_FAILURE("네트워크에 연결할 수 없습니다. 네트워크 연결을 확인해주세요."),
    QUERY_IS_NONE("검색어를 입력해주세요.")
}