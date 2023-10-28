package com.cjimenezro.superheroes.features.list.presentation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.cjimenezro.superheroes.R

class SuperHeroesDetailAdapter : ListAdapter<String,SuperHeroesDetailViewHolder>(SuperHeroesDetailsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_super_heroe_detail_item,parent,false)
        return SuperHeroesDetailViewHolder(view)
    }

    override fun getItemCount(): Int =currentList.size

    override fun onBindViewHolder(holder: SuperHeroesDetailViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


}