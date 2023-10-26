package com.cjimenezro.superheroes.features.list.presentation

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe

class SuperHeroesDiffUtil: DiffUtil.ItemCallback<SuperHeroe>() {
    override fun areItemsTheSame(oldItem: SuperHeroe, newItem: SuperHeroe): Boolean {
        return oldItem.principalData.id==newItem.principalData.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SuperHeroe, newItem: SuperHeroe): Boolean {
        return oldItem == newItem
    }
}