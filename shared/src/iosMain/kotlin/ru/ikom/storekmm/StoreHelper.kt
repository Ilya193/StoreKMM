package ru.ikom.storekmm

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import ru.ikom.storekmm.di.sharedModule
import ru.ikom.storekmm.domain.StoreRepository

class StoreHelper: KoinComponent {
    val storeRepository: StoreRepository by inject()
}

fun initKoin() {
    startKoin { modules(sharedModule()) }
}