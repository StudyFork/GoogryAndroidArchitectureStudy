package io.github.sooakim.ui

import io.github.sooakim.ui.base.SAState

sealed class SASplashState : SAState {
    class ShowMain : SASplashState()

    class ShowLogin : SASplashState()
}