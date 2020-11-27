package com.distin.placeit.ui.restaurant

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.distin.placeit.R
import com.distin.placeit.utils.ext.observe
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_restaurant.*
import org.koin.android.viewmodel.ext.android.viewModel


class RestaurantFragment : Fragment() {

    private val viewModel: RestaurantViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val restaurantAdapter = RestaurantAdapter()

        getLocation()
        observe(viewModel.getRestaurant()) {
            Log.d("cekdata", it.toString())
            restaurantAdapter.setData(it)
        }

        with(rv_restaurant) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = restaurantAdapter
        }
        restaurantAdapter.onItemClick = { selectedData ->

        }
    }

    private fun getLocation() {
        val mFusedLocation = LocationServices.getFusedLocationProviderClient(activity ?: return)
        if (ActivityCompat.checkSelfPermission(
                context ?: return,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context ?: return,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

        } else {
            mFusedLocation.lastLocation.addOnSuccessListener(
                activity ?: return
            ) { loc ->
                viewModel.fetchRestaurant("${loc.latitude}, ${loc.longitude}")
            }
        }
    }

}