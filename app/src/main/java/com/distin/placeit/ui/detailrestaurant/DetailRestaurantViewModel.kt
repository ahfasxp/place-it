package com.distin.placeit.ui.detailrestaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.distin.placeit.core.data.network.ApiService
import com.distin.placeit.core.data.response.RestaurantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DetailRestaurantViewModel(private val apiService: ApiService) : ViewModel() {

    private val restaurant = MutableLiveData<RestaurantResponse>()

    fun getRestaurant(): LiveData<RestaurantResponse> = restaurant

    fun fetchRestaurant(id: String?) {
        viewModelScope.launch {
            flow {
                try {
                    val response = apiService.getDetailRestaurant(id).results
                    emit(response)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }.flowOn(Dispatchers.IO).collect { restaurant.postValue(it) }
        }
    }
}