package com.distin.placeit.di

import com.distin.placeit.ui.detailrestaurant.DetailRestaurantViewModel
import com.distin.placeit.ui.restaurant.RestaurantViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RestaurantViewModel(get()) }
    viewModel { DetailRestaurantViewModel(get()) }
}