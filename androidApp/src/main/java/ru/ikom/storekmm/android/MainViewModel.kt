package ru.ikom.storekmm.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ikom.storekmm.domain.LoadResult
import ru.ikom.storekmm.domain.StoreRepository
import ru.ikom.storekmm.android.Mappers.toGoodUi

class MainViewModel(
    private val repository: StoreRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow(GoodsUiState(isLoading = true))
    val uiState: StateFlow<GoodsUiState> get() = _uiState.asStateFlow()

    fun fetchData() = viewModelScope.launch(dispatcher) {
        _uiState.value = GoodsUiState(isLoading = true)
        when (val res = repository.fetchGoods()) {
            is LoadResult.Success -> _uiState.value = GoodsUiState(goods = res.data.map { it.toGoodUi() })
            is LoadResult.Error -> _uiState.value = GoodsUiState(isError = true)
        }
    }
}

data class GoodsUiState(
    val goods: List<GoodUi> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

data class GoodUi(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)