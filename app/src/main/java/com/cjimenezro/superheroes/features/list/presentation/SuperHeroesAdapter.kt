package com.cjimenezro.superheroes.features.list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.cjimenezro.superheroes.R
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe

class SuperHeroesAdapter : ListAdapter<SuperHeroe,SuperHeroesViewHolder>(SuperHeroesDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_super_heore_item,parent,false)
        return SuperHeroesViewHolder(view)
    }

    override fun getItemCount(): Int =currentList.size

    override fun onBindViewHolder(holder: SuperHeroesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}