package com.H5210037_omer_albayrak.Final.util

import android.widget.ImageView
import com.H5210037_omer_albayrak.Final.R
import com.bumptech.glide.Glide

//Class açıklaması
//Bu class sayesinde internetten resimlerimizi çekiyoruz

fun ImageView.getFromUrl(url: String) {
    Glide.with(context)
        .load(url)
        .error(R.mipmap.ic_launcher) //Error halinde gelecek fotoğraf
        .fitCenter() //Ortaya sığması için gereken kod
        .into(this) //this'e yani yazılan yere imagei getirmesi için gereken kod
}