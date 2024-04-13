package ru.ikom.storekmm.data

import kotlinx.serialization.Serializable
import ru.ikom.storekmm.domain.GoodDomain

@Serializable
internal data class GoodsCloud(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
) {
    fun toGoodsDomain(): List<GoodDomain> = products.map { it.toGoodDomain() }
}