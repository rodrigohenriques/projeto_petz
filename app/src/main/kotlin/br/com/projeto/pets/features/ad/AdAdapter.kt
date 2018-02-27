package br.com.projeto.pets.features.ad

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.projeto.pets.R
import com.squareup.picasso.Picasso

class AdAdapter constructor(private val context: Context) : RecyclerView.Adapter<AdAdapter.AdHolder>() {
  private val ads: MutableList<Ad> = mutableListOf()

  override fun onBindViewHolder(holder: AdHolder?, position: Int) {
    holder?.let {
      val ad = ads.get(position)
      it.name.text = ad.city
      it.price.text = ad.price.toString()
      it.type.text = ad.city
      Picasso.with(context)
          .load(ad.photos.get(0).photo)
          .into(it.dogImage)
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
