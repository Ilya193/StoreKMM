package ru.ikom.storekmm.di

import org.koin.dsl.module
import ru.ikom.storekmm.data.notes.NotesRepositoryImpl
import ru.ikom.storekmm.domain.notes.NotesRepository

val databaseModule = module {
    factory<NotesRepository> {
        NotesRepositoryImpl(get())
    }
}