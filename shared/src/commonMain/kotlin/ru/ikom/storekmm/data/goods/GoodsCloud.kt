package ru.ikom.storekmm.data.goods

import kotlinx.serialization.Serializable
import ru.ikom.storekmm.domain.goods.GoodDomain

@Serializable
internal data class GoodsCloud(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
) {
    fun toGoodsDomain(): List<GoodDomain> = products.map { it.toGoodDomain() }
}