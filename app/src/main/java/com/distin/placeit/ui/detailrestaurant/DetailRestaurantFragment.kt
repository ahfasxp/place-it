package com.distin.placeit.ui.detailrestaurant

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.distin.placeit.R
import com.distin.placeit.utils.ext.observe
import kotlinx.android.synthetic.main.fragment_detail_restaurant.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


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
            Log.d("cekdata1", it.toString())

            tv_title.text = it.name
            if (it.rating != null) {
                tv_rating.text = getString(R.string.rating_restaurant, it.rating)
            } else tv_rating.visibility = View.GONE
            tv_description.text = getString(
                R.string.description_restaurant,
                it.types[0].capitalize(Locale.ROOT)
            )
            tv_distance.text = getString(
                R.string.distance_restaurant,
                it.getDistance().toString()
            )
            if (it.openingHours?.openNow != null) {
                tv_status.text = it.openingHours.isOpen()
            } else tv_status.visibility = View.GONE
            if (it.formatted_phone_number != null) {
                tv_phone.text = it.formatted_phone_number
            } else tv_phone.visibility = View.GONE
            tv_address.text = it.formatted_address

            imageAdapter.setData(it.photos)
            is_photo.setSliderAdapter(imageAdapter)
            is_photo.setInfiniteAdapterEnabled(false)
        }
    }
}