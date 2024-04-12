package ru.ikom.storekmm.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ikom.storekmm.LoadResult
import ru.ikom.storekmm.StoreRepository
import ru.ikom.storekmm.android.Mappers.toGoodUi

class MainViewModel(
    private val repository: StoreRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow<GoodsUiState>(GoodsUiState())
    val uiState: StateFlow<GoodsUiState> get() = _uiState.asStateFlow()

    fun fetchData() = viewModelScope.launch(dispatcher) {
        when (val res = repository.fetchGoods()) {
            is LoadResult.Success -> _uiState.value = GoodsUiState(goods = res.data.map { it.toGoodUi() })
            is LoadResult.Error -> _uiState.value = GoodsUiState(error = true)
        }
    }
}

data class GoodsUiState(
    val goods: List<GoodUi> = emptyList(),
    val error: Boolean = false
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