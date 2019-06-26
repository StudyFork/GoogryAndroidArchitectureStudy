package dev.daeyeon.gaasproject.module

import dev.daeyeon.gaasproject.data.source.UpbitDataSource
import dev.daeyeon.gaasproject.data.source.UpbitRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<UpbitDataSource> { UpbitRepository(get()) }
}
