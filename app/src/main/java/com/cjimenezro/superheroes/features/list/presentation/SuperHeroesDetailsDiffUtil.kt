package com.cjimenezro.superheroes.features.list.presentation

import androidx.recyclerview.widget.DiffUtil

class SuperHeroesDetailsDiffUtil:DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        TODO("Not yet implemented")
    }
}