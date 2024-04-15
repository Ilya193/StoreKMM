package ru.ikom.storekmm.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.ikom.storekmm.android.Mappers.toNoteUi
import ru.ikom.storekmm.domain.notes.NotesRepository

class DatabaseViewModel(
    private val repository: NotesRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> get() = _uiState.asStateFlow()

    fun fetchNotes() = viewModelScope.launch(dispatcher) {
        val notes = repository.fetchNotes().map { it.toNoteUi() }
        _uiState.value = NotesUiState(notes = notes.toList())
    }

    fun event(event: Event) = viewModelScope.launch(dispatcher) {
        when (event) {
            is Event.ShowDialog -> showDialog(event.showDialog)
            is Event.AddNote -> insertNote(event.title)
            is Event.DeleteNote -> deleteNoteById(event.id)
        }
    }

    private fun showDialog(showDialog: Boolean) {
        _uiState.update { it.copy(showDialog = showDialog) }
    }

    private suspend fun insertNote(title: String) {
        repository.insertNote(title)
        fetchNotes()
    }

    private suspend fun deleteNoteById(id: Long) {
        repository.deleteById(id)
        fetchNotes()
    }
}

data class NotesUiState(
    val notes: List<NoteUi> = emptyList(),
    val showDialog: Boolean = false
)

sealed interface Event {
    data class ShowDialog(val showDialog: Boolean) : Event
    data class AddNote(val title: String): Event
    data class DeleteNote(val id: Long): Event
}
