package com.distin.placeit.ui.detailrestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.distin.placeit.R
import com.distin.placeit.core.data.response.RestaurantResponse
import kotlinx.android.synthetic.main.item_list_image.view.*

class DetailRestaurantAdapter :
    RecyclerView.Adapter<DetailRestaurantAdapter.DetailImageViewHolder>() {
    private var listDetailPhoto = ArrayList<RestaurantResponse>()

    fun setData(detailPhoto: List<RestaurantResponse>?) {
        if (detailPhoto == null) return
        listDetailPhoto.clear()
        listDetailPhoto.addAll(detailPhoto)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailImageViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_image, parent, false)
        return DetailImageViewHolder(mView)
    }

    override fun onBindViewHolder(
        holder: DetailImageViewHolder,
        position: Int
    ) {
        val detailRestaurant = listDetailPhoto[position]
        holder.bind(detailRestaurant)
    }

    override fun getItemCount(): Int = listDetailPhoto.size

    class DetailImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(detailPhoto: RestaurantResponse) {
            with(itemView) {
                Glide.with(context)
                    .load(
                        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=${
                            detailPhoto.photos?.get(
                                0
                            )?.photoReference
                        }&key=AIzaSyCXDNrjopqcUMC7WF7VOBJv_NgtEXURTNI"
                    )
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_photo)
            }
        }
    }
}