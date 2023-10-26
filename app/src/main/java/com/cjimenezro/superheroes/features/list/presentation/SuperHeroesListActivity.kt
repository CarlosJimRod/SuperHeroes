package com.cjimenezro.superheroes.features.list.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.extensions.show
import com.cjimenezro.superheroes.app.serialization.GsonSerialization
import com.cjimenezro.superheroes.databinding.ActivitySuperHeroeBinding
import com.cjimenezro.superheroes.features.list.data.BiographyDataRepository
import com.cjimenezro.superheroes.features.list.data.SuperHeroeDataRepository
import com.cjimenezro.superheroes.features.list.data.WorkDataRepository
import com.cjimenezro.superheroes.features.list.data.local.BiographyLocalDataSource
import com.cjimenezro.superheroes.features.list.data.local.SuperHeroeLocalDataSource
import com.cjimenezro.superheroes.features.list.data.local.WorkLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.domain.GetSuperHeroeUseCase
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe
import com.google.android.material.snackbar.Snackbar

class SuperHeroesListActivity : AppCompatActivity() {

    lateinit var binding : ActivitySuperHeroeBinding

    val viewModel:SuperHeroesListViewModel by lazy {
        SuperHeroesListViewModel(GetSuperHeroeUseCase(
            SuperHeroeDataRepository(SuperHeroeLocalDataSource(this,GsonSerialization()),SuperHeroesApiClient()),
            BiographyDataRepository(BiographyLocalDataSource(this,GsonSerialization()),SuperHeroesApiClient()),
            WorkDataRepository(WorkLocalDataSource(this,GsonSerialization()),SuperHeroesApiClient())
        ))
    }

    private val superHeroApadter = SuperHeroesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupView()
        setupObservers()
        viewModel.loadSuperHeroe()
    }

    private fun setupBinding() {
        binding = ActivitySuperHeroeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView(){
        binding.apply {
            list.layoutManager = LinearLayoutManager(
                this@SuperHeroesListActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            list.adapter = superHeroApadter
        }
    }

    private fun setupObservers(){
        val observer= Observer<SuperHeroesListViewModel.UiState>{
            if (it.isLoading){
                Snackbar.make(binding.root,"Cargando ...", Snackbar.LENGTH_SHORT).show()
                showLoading()
            }else{
                hideLoading()
            }
            it.errorApp?.apply {
                showError(this)
            }

            it.superHeroe?.apply {
                bind(this)
            }
        }
        viewModel.uiState.observe(this,observer)
    }

    private fun showLoading(){
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading(){
        binding.skeletonLayout.showOriginal()
    }

    private fun showError(error: ErrorApp) {
        binding.viewError.root.show()
    }

    private fun bind(superHeroes:List<SuperHeroe>){
        superHeroApadter.submitList(superHeroes)
    }
}