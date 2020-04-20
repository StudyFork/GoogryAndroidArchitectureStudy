package com.example.data.mapper

import com.example.data.model.Auth as dataAuth
import com.example.local.model.Auth as localAuth

object AuthMapper {
    fun localAuthToDataAuth(auth: localAuth): dataAuth {
        return dataAuth(autoLogin = auth.autoLogin)
    }

    fun dataAuthToLocalAuth(auth: dataAuth): localAuth {
        return localAuth(autoLogin = auth.autoLogin)
    }
}