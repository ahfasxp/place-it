package com.distin.placeit.ui.restaurant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.distin.placeit.R
import com.distin.placeit.core.data.response.RestaurantResponse
import kotlinx.android.synthetic.main.item_list.view.*
import java.util.*
import kotlin.collections.ArrayList

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    private var listRestaurants = ArrayList<RestaurantResponse>()
    var onItemClick: ((RestaurantResponse) -> Unit)? = null

    fun setData(restaurant: List<RestaurantResponse>?) {
        if (restaurant == null) return
        listRestaurants.clear()
        listRestaurants.addAll(restaurant)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return RestaurantViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = listRestaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int = listRestaurants.size

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(restaurant: RestaurantResponse) {
            with(itemView) {
                Glide.with(context)
                    .load(
                        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=${
                            restaurant.photos?.get(
                                0
                            )?.photoReference
                        }&key=AIzaSyCXDNrjopqcUMC7WF7VOBJv_NgtEXURTNI"
                    )
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_photo)
                tv_title.text = restaurant.name
                tv_rating.text = restaurant.rating
                tv_description.text = restaurant.types[0].capitalize(Locale.ROOT)
                tv_distance.text = restaurant.getDistance().toString() + " m"

                itemView.setOnClickListener {
                    onItemClick?.invoke(listRestaurants[adapterPosition])
                }
            }
        }
    }
}