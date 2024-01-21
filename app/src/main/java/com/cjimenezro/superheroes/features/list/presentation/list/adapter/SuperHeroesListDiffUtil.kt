package com.cjimenezro.superheroes.features.list.presentation.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe

class SuperHeroesListDiffUtil: DiffUtil.ItemCallback<SuperHeroe>() {
    override fun areItemsTheSame(oldItem: SuperHeroe, newItem: SuperHeroe): Boolean {
        return oldItem.principalData.id==newItem.principalData.id
    }

    override fun areContentsTheSame(oldItem: SuperHeroe, newItem: SuperHeroe): Boolean {
        return oldItem == newItem
    }
}