package ru.ikom.storekmm.android

import ru.ikom.storekmm.domain.GoodDomain

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
}