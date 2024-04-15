package ru.ikom.storekmm.android

import ru.ikom.storekmm.domain.goods.GoodDomain
import ru.ikom.storekmm.domain.notes.NoteDomain

object Mappers {

    fun GoodDomain.toGoodUi(): GoodUi =
        GoodUi(
            brand,
            category,
            description,
            discountPercentage,
            id,
            images,
            price,
            rating,
            stock,
            thumbnail,
            title
        )

    fun NoteDomain.toNoteUi(): NoteUi = NoteUi(id, title)
}