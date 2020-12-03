package com.distin.placeit.ui.detailrestaurant

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.distin.placeit.R
import com.distin.placeit.utils.ext.observe
import org.koin.android.viewmodel.ext.android.viewModel


class DetailRestaurantFragment : Fragment() {

    private val viewModel: DetailRestaurantViewModel by viewModel()

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
        viewModel.fetchRestaurant(placeId)

        val imageAdapter = ImageSliderAdapter()
        observe(viewModel.getRestaurant()) {
//            imageAdapter.setData(it)
            Log.d("cekdata1", it.toString())
        }

//        is_photo.setSliderAdapter(imageAdapter)
    }
}