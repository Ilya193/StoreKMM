package ru.ikom.storekmm.data.goods

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.ikom.storekmm.domain.ErrorType
import ru.ikom.storekmm.domain.goods.GoodsLoadResult
import ru.ikom.storekmm.domain.goods.StoreRepository

internal class StoreRepositoryImpl(
    private val client: HttpClient
) : StoreRepository {
    override suspend fun fetchGoods(): GoodsLoadResult {
        return try {
            val goods = client.get("https://dummyjson.com/products").body<GoodsCloud>().toGoodsDomain()
            GoodsLoadResult.Success(goods)
        }
        catch (e: Exception) {
            GoodsLoadResult.Error(ErrorType.GENERIC_ERROR)
        }
    }
}