package ru.ikom.storekmm.domain.goods

import ru.ikom.storekmm.domain.ErrorType

sealed class GoodsLoadResult {

    data class Success(
        val data: List<GoodDomain>
    ): GoodsLoadResult()

    data class Error(
        val e: ErrorType
    ): GoodsLoadResult()

}