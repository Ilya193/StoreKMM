package ru.ikom.storekmm

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class StoreRepositoryImpl(
    private val client: HttpClient
) : StoreRepository {
    override suspend fun fetchGoods(): LoadResult<List<GoodDomain>> {
        return try {
            val goods = client.get("https://dummyjson.com/products").body<GoodsCloud>()
            LoadResult.Success(goods.toGoodsDomain())
        }
        catch (e: Exception) {
            println("s149 $e")
            LoadResult.Error(ErrorType.GENERIC_ERROR)
        }
    }
}