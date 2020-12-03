package com.distin.placeit.ui.detailrestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.distin.placeit.R
import com.distin.placeit.core.data.response.PhotosResponse
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_list_image.view.*

class ImageSliderAdapter : SliderViewAdapter<ImageSliderAdapter.ImageSliderVH>() {
    private var listDetailPhoto = ArrayList<PhotosResponse>()

    fun setData(detailPhoto: List<PhotosResponse>?) {
        if (detailPhoto == null) return
        listDetailPhoto.clear()
        listDetailPhoto.addAll(detailPhoto)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ImageSliderVH {
        val mView = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_image, null)
        return ImageSliderVH(mView)
    }

    override fun onBindViewHolder(viewHolder: ImageSliderVH?, position: Int) {
        val detailRestaurant = listDetailPhoto[position]
        viewHolder?.bind(detailRestaurant)
    }

    override fun getCount(): Int = listDetailPhoto.size

    class ImageSliderVH(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        fun bind(detailPhoto: PhotosResponse) {
            with(itemView) {
                Glide.with(itemView)
                    .load(
                        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=${
                            detailPhoto.photoReference
                        }&key=AIzaSyCXDNrjopqcUMC7WF7VOBJv_NgtEXURTNI"
                    ).apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_photo)
            }
        }
    }
}