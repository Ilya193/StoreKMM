package ru.ikom.storekmm

import kotlinx.serialization.Serializable

@Serializable
internal data class GoodsCloud(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
) {
    fun toGoodsDomain(): List<GoodDomain> = products.map { it.toGoodDomain() }
}