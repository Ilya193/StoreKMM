package ru.ikom.storekmm.domain

interface StoreRepository {
    suspend fun fetchGoods(): LoadResult
}