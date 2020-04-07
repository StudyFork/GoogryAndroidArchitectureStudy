package io.github.sooakim.ui

import io.github.sooakim.ui.base.SAState

sealed class SASplashState : SAState {
    object ShowMain : SASplashState()

    object ShowLogin : SASplashState()
}