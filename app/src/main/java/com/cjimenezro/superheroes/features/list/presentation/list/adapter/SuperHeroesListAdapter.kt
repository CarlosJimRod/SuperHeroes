package com.cjimenezro.superheroes.features.list.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.cjimenezro.superheroes.R
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe

class SuperHeroesListAdapter : ListAdapter<SuperHeroe, SuperHeroesListViewHolder>(
    SuperHeroesListDiffUtil()
){

    lateinit var onClick:(heroId:String) -> Unit

    fun setEvent(onClick:(String) -> Unit){
        this.onClick=onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_super_heore_item,parent,false)
        return SuperHeroesListViewHolder(view)
    }

    override fun getItemCount(): Int =currentList.size

    override fun onBindViewHolder(holder: SuperHeroesListViewHolder, position: Int) {
        holder.bind(currentList[position],onClick)
    }
}