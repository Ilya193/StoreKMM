package ru.ikom.storekmm.domain

sealed class LoadResult {

    data class Success(
        val data: List<GoodDomain>
    ): LoadResult()

    data class Error(
        val e: ErrorType
    ): LoadResult()

}