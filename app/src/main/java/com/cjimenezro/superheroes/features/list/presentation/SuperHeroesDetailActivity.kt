package com.cjimenezro.superheroes.features.list.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.cjimenezro.superheroes.R
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.extensions.setUrl
import com.cjimenezro.superheroes.app.extensions.show
import com.cjimenezro.superheroes.app.serialization.GsonSerialization
import com.cjimenezro.superheroes.databinding.ActivitySuperHeroeDetailsBinding
import com.cjimenezro.superheroes.features.list.data.BiographyDataRepository
import com.cjimenezro.superheroes.features.list.data.SuperHeroeDataRepository
import com.cjimenezro.superheroes.features.list.data.WorkDataRepository
import com.cjimenezro.superheroes.features.list.data.local.BiographyLocalDataSource
import com.cjimenezro.superheroes.features.list.data.local.SuperHeroeLocalDataSource
import com.cjimenezro.superheroes.features.list.data.local.WorkLocalDataSource
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.domain.GetSuperHeroeByIdUseCase
import com.cjimenezro.superheroes.features.list.domain.GetSuperHeroeUseCase
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe
import com.faltenreich.skeletonlayout.Skeleton
import com.google.android.material.snackbar.Snackbar

class SuperHeroesDetailActivity : AppCompatActivity() {

    lateinit var binding : ActivitySuperHeroeDetailsBinding

    private val superHeroesApiClient=SuperHeroesApiClient()

    private val superHeroeDetailAdapter=SuperHeroesDetailAdapter()

    val viewModel:SuperHeroesDetailViewModel by lazy {
        SuperHeroesDetailViewModel(
            GetSuperHeroeByIdUseCase(
            SuperHeroeDataRepository(
                SuperHeroeLocalDataSource(this, GsonSerialization()),
                superHeroesApiClient
            ),
            BiographyDataRepository(
                BiographyLocalDataSource(this, GsonSerialization()),
                superHeroesApiClient
            ),
            WorkDataRepository(
                WorkLocalDataSource(this, GsonSerialization()),
                superHeroesApiClient
            )
        )
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        val heroId=intent.extras?.getInt(HERO_ID_PARAM)
        setupObservers(heroId!!)
        setupView()
        viewModel.loadSuperHeroe(heroId.toString())
    }

    private fun setupBinding() {
        binding = ActivitySuperHeroeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView(){
        binding.apply {
            listImagenes.layoutManager= LinearLayoutManager(
                this@SuperHeroesDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            listImagenes.adapter= superHeroeDetailAdapter
        }
    }

    private fun setupObservers(id:Int){
        val observer= Observer<SuperHeroesDetailViewModel.UiState>{
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
                bind(this,id)
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

    private fun bind(superHeroe:SuperHeroe,id:Int){
        binding. apply {
            imageSuperHeroeDetail.setUrl(superHeroe.principalData.imageUrl[0])
            nameSuperHeroeDetail.text=superHeroe.principalData.name
            stateSuperHeroeDetail.text=superHeroe.biography.alignment
            descriptionSuperHeroeDetail.text=superHeroe.biography.fullName
            attributesSuperHeroeDetail.findViewById<ConstraintLayout>(R.id.intelligence).findViewById<TextView>(R.id.number1).text=superHeroe.principalData.stats[0]
            attributesSuperHeroeDetail.findViewById<ConstraintLayout>(R.id.speed).findViewById<TextView>(R.id.number2).text=superHeroe.principalData.stats[1]
            attributesSuperHeroeDetail.findViewById<ConstraintLayout>(R.id.combat).findViewById<TextView>(R.id.number3).text=superHeroe.principalData.stats[2]
        }
        superHeroeDetailAdapter.submitList(superHeroe.principalData.imageUrl )

    }

    companion object{
        val HERO_ID_PARAM="KEY_HERO_ID"
    }

}