package com.cjimenezro.superheroes.features.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjimenezro.superheroes.R
import com.cjimenezro.superheroes.app.ErrorApp
import com.cjimenezro.superheroes.app.extensions.setUrl
import com.cjimenezro.superheroes.app.extensions.show
import com.cjimenezro.superheroes.databinding.FragmentSuperHeroeDetailsBinding
import com.cjimenezro.superheroes.features.list.MainActivity
import com.cjimenezro.superheroes.features.list.data.remote.SuperHeroesApiClient
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe
import com.google.android.material.snackbar.Snackbar

class SuperHeroesDetailFragment : Fragment() {

    private var _binding: FragmentSuperHeroeDetailsBinding? = null
    private val binding get() = _binding!!

    val viewModel by viewModels<SuperHeroesDetailViewModel>()

    private val superHeroesApiClient=SuperHeroesApiClient()

    private val superHeroeDetailAdapter=SuperHeroesDetailAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container:ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperHeroeDetailsBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
        /*
        val heroId=intent.extras?.getInt(HERO_ID_PARAM)
        setupObservers(heroId!!)
        viewModel.loadSuperHeroe(heroId.toString())*/
    }

    private fun getParams(): String = arguments?.getString("ARG_PARAM1") ?: ""

    private fun setupView(){
        binding.apply {
            listImagenes.layoutManager= LinearLayoutManager(
                this@SuperHeroesDetailFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            listImagenes.adapter= superHeroeDetailAdapter
            layoutDetail.toolbar.setOnClickListener {
                (activity as MainActivity).changeFragment(SuperHeroesListFragment.newInstance())
            }
            layoutDetail.toolbar.apply {
                //Establecemos el título por programación
                title = getString(R.string.fragment_detail_title)

                //Inflamos|Añadimos el menú por programación.
                inflateMenu(R.menu.menu_detail)

                //Añadimos el icono por programación
                setNavigationIcon(R.drawable.ic_close)

                //Añadimos una acción al botón de navegación
                setNavigationOnClickListener {
                    //Volver a atrás...
                }

                //Añadimos los eventos a los botones del menú
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_add -> {
                            //Mostramos un mensaje
                            addItem()
                            //Siempre hay que devolver true para que quede pulsado
                            true
                        }

                        else -> false
                    }
                }
            }
        }
    }

    private fun addItem() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val heroId=getParams()
        setupObservers()
    }

    private fun setupObservers(){
        val observer= Observer<SuperHeroesDetailViewModel.UiState>{
            if (it.isLoading){
                Snackbar.make(binding.root,"Cargando ...", Snackbar.LENGTH_SHORT).show()
                showLoading()
            }else{
                hideLoading()
            }
            it.errorApp?.apply {
                //showError(this)
            }

            it.superHeroe?.apply {
                bind(this)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner,observer)
    }

    private fun showLoading(){
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading(){
        binding.skeletonLayout.showOriginal()
    }

    /*private fun showError(error: ErrorApp) {
        binding.viewError.root.show()
    }*/

    private fun bind(superHeroe:SuperHeroe){
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(param1: String) =
            SuperHeroesDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                }
            }
    }

}