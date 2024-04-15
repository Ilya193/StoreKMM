package ru.ikom.storekmm.domain.goods

interface StoreRepository {
    suspend fun fetchGoods(): LoadResult
}