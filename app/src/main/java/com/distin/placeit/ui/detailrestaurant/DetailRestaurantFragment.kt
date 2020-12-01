package com.distin.placeit.ui.detailrestaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.distin.placeit.R
import kotlinx.android.synthetic.main.fragment_detail_restaurant.*

class DetailRestaurantFragment : Fragment() {
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
        tv_placeId.text = placeId
    }
}