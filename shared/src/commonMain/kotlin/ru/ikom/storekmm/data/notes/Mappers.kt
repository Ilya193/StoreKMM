package ru.ikom.storekmm.data.notes

import ru.ikom.storekmm.Note
import ru.ikom.storekmm.domain.notes.NoteDomain

internal object Mappers {
    fun Note.toNoteDomain(): NoteDomain = NoteDomain(id, title)
}