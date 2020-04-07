package io.github.sooakim.ui.login

import io.github.sooakim.ui.base.SAState

sealed class SALoginState : SAState {
    object ShowMain : SALoginState()
}