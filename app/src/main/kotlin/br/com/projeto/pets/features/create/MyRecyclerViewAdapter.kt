package br.com.projeto.pets.features.create

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.projeto.pets.R
import br.com.projeto.pets.extension.setImageBase64
import br.com.projeto.pets.util.Base64Convertor.encodeToBase64
import com.yanzhenjie.album.AlbumFile

class MyRecyclerViewAdapter(
        val context: Context,
        var data: ArrayList<AlbumFile>) : RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_pet_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val albumFile = data[position]
        holder.myImageView.setImageBase64(encodeToBase64(BitmapFactory.decodeFile(albumFile.path),Bitmap.CompressFormat.PNG,100))
    }

    fun updateData(newListFile: ArrayList<AlbumFile>) {
        data = newListFile
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myImageView: ImageView = itemView.findViewById(R.id.pet_image)
    }

}