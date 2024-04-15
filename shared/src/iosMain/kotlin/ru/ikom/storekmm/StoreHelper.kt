package ru.ikom.storekmm

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import ru.ikom.storekmm.di.sharedModule
import ru.ikom.storekmm.domain.goods.StoreRepository
import ru.ikom.storekmm.domain.notes.NotesRepository

class StoreHelper: KoinComponent {
    private val storeRepository: StoreRepository by inject()
    private val notesRepository: NotesRepository by inject()

    fun injectStoreRepository(): StoreRepository = storeRepository
    fun injectNotesRepository(): NotesRepository = notesRepository
}

fun initKoin() {
    startKoin { modules(sharedModule()) }
}