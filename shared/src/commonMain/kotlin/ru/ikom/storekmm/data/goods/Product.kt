package ru.ikom.storekmm.data.goods

import kotlinx.serialization.Serializable
import ru.ikom.storekmm.domain.goods.GoodDomain

@Serializable
internal data class Product(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String,
) {
    fun toGoodDomain(): GoodDomain =
        GoodDomain(
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