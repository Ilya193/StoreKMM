package ru.ikom.storekmm.domain.notes

interface NotesRepository {
    suspend fun fetchNotes(): List<NoteDomain>
    suspend fun insertNote(title: String)
    suspend fun deleteById(id: Long)
}