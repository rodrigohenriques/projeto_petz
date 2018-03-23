package br.com.projeto.pets.extension

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView

fun ImageView.setImageBase64(image: String) {
    val imageAsBytes = Base64.decode(image
            .replace("data:image/jpeg;base64,", ""), Base64.DEFAULT)

    BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)?.let {
        setImageBitmap(it)
    }
}
