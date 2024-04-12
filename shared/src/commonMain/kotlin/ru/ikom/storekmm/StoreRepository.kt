package ru.ikom.storekmm

interface StoreRepository {
    suspend fun fetchGoods(): LoadResult<List<GoodDomain>>
}