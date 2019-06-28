package dev.daeyeon.gaasproject.module

import dev.daeyeon.gaasproject.network.NetworkManager
import org.koin.dsl.module

val networkModule = module {

    single { NetworkManager.instance }
}