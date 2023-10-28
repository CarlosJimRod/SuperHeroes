package com.cjimenezro.superheroes.features.list.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjimenezro.superheroes.R
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
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.snackbar.Snackbar

class SuperHeroesListActivity : AppCompatActivity() {

    lateinit var binding : ActivitySuperHeroeBinding

    private val superHeroesApiClient=SuperHeroesApiClient()

    private lateinit var skeleton:Skeleton

    val viewModel:SuperHeroesListViewModel by lazy {
        SuperHeroesListViewModel(GetSuperHeroeUseCase(
            SuperHeroeDataRepository(SuperHeroeLocalDataSource(this,GsonSerialization()),superHeroesApiClient),
            BiographyDataRepository(BiographyLocalDataSource(this,GsonSerialization()),superHeroesApiClient),
            WorkDataRepository(WorkLocalDataSource(this,GsonSerialization()),superHeroesApiClient)
        ))
    }

    private val superHeroApadter = SuperHeroesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupView()
        setupObservers()
        skeleton=binding.list.applySkeleton(R.layout.view_super_heore_item,8)
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
            superHeroApadter.setEvent {
                navigateToDetail(it)
            }
            list.adapter = superHeroApadter
        }
    }

    private fun navigateToDetail(heroId: Int){
        val intent = Intent(this,SuperHeroesDetailActivity::class.java)
        intent.putExtra("KEY_HERO_ID",heroId)
        startActivity(intent)
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
        skeleton.showSkeleton()
    }

    private fun hideLoading(){
        skeleton.showOriginal()
    }

    private fun showError(error: ErrorApp) {
        binding.viewError.root.show()
    }

    private fun bind(superHeroes:List<SuperHeroe>){
        superHeroApadter.submitList(superHeroes)
    }
}