package ru.ikom.storekmm.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.ikom.storekmm.domain.ErrorType
import ru.ikom.storekmm.domain.LoadResult
import ru.ikom.storekmm.domain.StoreRepository

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