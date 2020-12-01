package com.distin.placeit.ui.detailrestaurant

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.distin.placeit.R
import com.distin.placeit.ui.restaurant.RestaurantViewModel
import com.distin.placeit.utils.ext.observe
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_detail_restaurant.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailRestaurantFragment : Fragment() {
    private val viewModel: RestaurantViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val placeId = DetailRestaurantFragmentArgs.fromBundle(arguments as Bundle).placeId

        val restaurantAdapter = DetailRestaurantAdapter()

        getLocation()

        observe(viewModel.getRestaurant()) {
            restaurantAdapter.setData(it)
            Log.d("cekdata12", it.toString())
        }

        with(rv_detail_image) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = restaurantAdapter
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