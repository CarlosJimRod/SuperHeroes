package com.cjimenezro.superheroes.features.list.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cjimenezro.superheroes.app.extensions.setUrl
import com.cjimenezro.superheroes.databinding.ViewSuperHeoreItemBinding
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe

class SuperHeroesViewHolder(val view: View):RecyclerView.ViewHolder(view) {

    private lateinit var binding : ViewSuperHeoreItemBinding
    fun bind(superHeroe: SuperHeroe, onClick: (Int) ->Unit){
        binding = ViewSuperHeoreItemBinding.bind(view)

        binding.apply {
            imageSuperHeore.setUrl(superHeroe.principalData.imageUrl[0])
            nameSuperHeroe.text=superHeroe.principalData.name
            descriptionSuperHeroe.text=superHeroe.biography.fullName
            workSuperHeroe.text=superHeroe.work.occupation
        }
        view.setOnClickListener{
            onClick.invoke(superHeroe.principalData.id)
        }
    }

}