package br.com.projeto.pets.features.ad

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.projeto.pets.R

class AdAdapter constructor(private val context: Context) : RecyclerView.Adapter<AdAdapter.AdHolder>() {
  private val ads: MutableList<Ad> = mutableListOf()

  override fun onBindViewHolder(holder: AdHolder?, position: Int) {
    holder?.let { entity ->
      val ad = ads.get(position)
      entity.name.text = ad.city
      entity.price.text = ad.price.toString()
      entity.type.text = ad.city

      val imageAsBytes = Base64.decode(ad.photos.get(0).photo, Base64.DEFAULT)

      BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)?.let { image ->
        entity.dogImage.setImageBitmap(image)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AdHolder? {
    return AdHolder(LayoutInflater.from(parent?.context)
        .inflate(R.layout.ad_item, parent, false))
  }

  override fun getItemCount() = ads.size

  fun addAds(newAds: List<Ad>) {
    ads.addAll(newAds)
    notifyDataSetChanged()
  }

  inner class AdHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var dogImage: ImageView = itemView.findViewById(R.id.dogImage)
    var type: TextView = itemView.findViewById(R.id.type)
    var name: TextView = itemView.findViewById(R.id.name)
    var price: TextView = itemView.findViewById(R.id.price)
  }
}
