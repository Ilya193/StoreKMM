package ru.ikom.storekmm.data.notes

import ru.ikom.storekmm.data.notes.Mappers.toNoteDomain
import ru.ikom.storekmm.domain.notes.NoteDomain
import ru.ikom.storekmm.domain.notes.NotesRepository
import sqldelight.ru.ikom.storekmm.AppDatabase

class NotesRepositoryImpl(
    private val db: AppDatabase
) : NotesRepository {
    override suspend fun fetchNotes(): List<NoteDomain> =
        db.appDatabaseQueries.selectAll().executeAsList().map { it.toNoteDomain() }

    override suspend fun insertNote(title: String) = db.appDatabaseQueries.insert(title)
    override suspend fun deleteById(id: Long) {
        db.appDatabaseQueries.deleteById(id)
    }
}