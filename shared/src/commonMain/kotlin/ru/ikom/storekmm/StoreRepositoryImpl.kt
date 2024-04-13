package ru.ikom.storekmm

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class StoreRepositoryImpl(
    private val client: HttpClient
) : StoreRepository {
    override suspend fun fetchGoods(): LoadResult {
        return try {
            val goods = client.get("https://dummyjson.com/products").body<GoodsCloud>().toGoodsDomain()
            LoadResult.Success(goods)
        }
        catch (e: Exception) {
            LoadResult.Error(ErrorType.GENERIC_ERROR)
        }
    }
}