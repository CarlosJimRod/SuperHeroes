package com.cjimenezro.superheroes.app.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setUrl(urlImage:String){
    Glide.with(this.context).load(urlImage).into(this)
}