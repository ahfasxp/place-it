package com.distin.placeit.ui.detailrestaurant

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.distin.placeit.R
import com.distin.placeit.utils.ext.observe
import kotlinx.android.synthetic.main.fragment_detail_restaurant.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class DetailRestaurantActivity : AppCompatActivity() {

    private val viewModel: DetailRestaurantViewModel by viewModel()

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_restaurant)

        val placeId = intent.getStringExtra(EXTRA_ID)
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