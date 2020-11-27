package com.distin.placeit.ui.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.distin.placeit.R
import com.distin.placeit.core.data.response.RestaurantResponse
import kotlinx.android.synthetic.main.item_list.view.*

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
        fun bind(restaurant: RestaurantResponse) {
            with(itemView) {
                Glide.with(context)
                    .load(restaurant.name)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_photo)
                tv_title.text = restaurant.name
                tv_rate.text = restaurant.placeId
                tv_description.text = restaurant.placeId
                tv_distance.text = restaurant.placeId

                itemView.setOnClickListener {
                    onItemClick?.invoke(listRestaurants[adapterPosition])
                }
            }
        }
    }
}