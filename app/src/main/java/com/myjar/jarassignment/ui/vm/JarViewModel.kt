package com.myjar.jarassignment.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myjar.jarassignment.Utils.Resource
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JarViewModel @Inject constructor(private val getProductsUseCase: GetProductsUseCase) : ViewModel() {

    private val _listStringData = MutableStateFlow<Resource<List<ComputerItem>>>(Resource.Loading)
    val listStringData: StateFlow<Resource<List<ComputerItem>>>
        get() = _listStringData

    private val _navigateToItem = MutableStateFlow<String?>(null)
    val navigateToItem: StateFlow<String?>
        get() = _navigateToItem


    fun fetchData() {
        _listStringData.value = Resource.Loading

        viewModelScope.launch {
            getProductsUseCase.invoke().collect{result->
                _listStringData.value = result
            }
         }
    }

    fun navigateToItemDetail(id: String) {
        viewModelScope.launch {
            _navigateToItem.emit(id)
        }
    }
}