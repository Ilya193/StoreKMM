package ru.ikom.storekmm.data.notes

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.ikom.storekmm.data.notes.Mappers.toNoteDomain
import ru.ikom.storekmm.domain.notes.NoteDomain
import ru.ikom.storekmm.domain.notes.NotesRepository
import sqldelight.ru.ikom.storekmm.AppDatabase

internal class NotesRepositoryImpl(
    private val db: AppDatabase
) : NotesRepository {
    override suspend fun fetchNotes(): Flow<List<NoteDomain>> = flow {
        db.appDatabaseQueries.selectAll().asFlow().mapToList(Dispatchers.IO).collect {
            emit(it.map { it.toNoteDomain() })
        }
    }

    override suspend fun insertNote(title: String) = db.appDatabaseQueries.insert(title)
    override suspend fun deleteById(id: Long) = db.appDatabaseQueries.deleteById(id)
}