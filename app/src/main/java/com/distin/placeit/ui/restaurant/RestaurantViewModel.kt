package com.distin.placeit.ui.restaurant

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.distin.placeit.core.data.network.ApiService
import com.distin.placeit.core.data.response.RestaurantResponse
import com.distin.placeit.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RestaurantViewModel(private val apiService: ApiService) : BaseViewModel() {

    private val restaurant = MutableLiveData<List<RestaurantResponse>>()

    fun getRestaurant(): LiveData<List<RestaurantResponse>> = restaurant

    @ExperimentalCoroutinesApi
    fun fetchRestaurant(location: String?) {
        viewModelScope.launch {
            flow {
                try {
                    val response = apiService.getRestaurant(location).results
                    if (response.isNotEmpty()) emit(response)
                } catch (e: Exception) {
                    Log.d("Failed fetch restaurant", e.toString())
                }
            }.flowOn(Dispatchers.IO).onStart {
                setLoading()
            }.collect {
                restaurant.postValue(it)
                finishLoading()
            }
        }
    }
}