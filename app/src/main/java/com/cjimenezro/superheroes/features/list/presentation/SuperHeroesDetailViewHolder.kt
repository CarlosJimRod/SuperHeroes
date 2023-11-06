package com.cjimenezro.superheroes.features.list.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cjimenezro.superheroes.app.extensions.setUrl
import com.cjimenezro.superheroes.databinding.ViewSuperHeoreItemBinding
import com.cjimenezro.superheroes.databinding.ViewSuperHeroeDetailItemBinding

class SuperHeroesDetailViewHolder(val view: View):RecyclerView.ViewHolder(view) {

    private lateinit var binding : ViewSuperHeroeDetailItemBinding

    fun bind(imagen:String){
        binding= ViewSuperHeroeDetailItemBinding.bind(view)

        binding.imagen.setUrl(imagen)
    }

}