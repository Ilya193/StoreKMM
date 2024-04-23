package ru.ikom.storekmm.domain.notes

import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun fetchNotes(): Flow<List<NoteDomain>>
    suspend fun insertNote(title: String)
    suspend fun deleteById(id: Long)
}