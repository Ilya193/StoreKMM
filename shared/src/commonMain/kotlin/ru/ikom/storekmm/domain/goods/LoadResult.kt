package ru.ikom.storekmm.domain.goods

import ru.ikom.storekmm.domain.ErrorType

sealed class LoadResult {

    data class Success(
        val data: List<GoodDomain>
    ): LoadResult()

    data class Error(
        val e: ErrorType
    ): LoadResult()

}