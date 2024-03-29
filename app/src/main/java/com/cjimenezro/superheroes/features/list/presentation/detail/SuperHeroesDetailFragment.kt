package com.cjimenezro.superheroes.features.list.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjimenezro.superheroes.R
import com.cjimenezro.superheroes.app.extensions.hide
import com.cjimenezro.superheroes.app.extensions.setUrl
import com.cjimenezro.superheroes.app.presentation.error.ErrorUiModel
import com.cjimenezro.superheroes.databinding.FragmentSuperHeroeDetailsBinding
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe
import com.cjimenezro.superheroes.features.list.presentation.detail.adapter.SuperHeroesDetailAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperHeroesDetailFragment : Fragment() {

    private var _binding: FragmentSuperHeroeDetailsBinding? = null
    private val binding get() = _binding!!

    val args: SuperHeroesDetailFragmentArgs by navArgs()

    val viewModel by viewModels<SuperHeroesDetailViewModel>()

    private val superHeroeDetailAdapter= SuperHeroesDetailAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container:ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperHeroeDetailsBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView(){
        binding.apply {
            listImagenes.layoutManager= LinearLayoutManager(
                this@SuperHeroesDetailFragment.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            listImagenes.adapter= superHeroeDetailAdapter
            layoutDetail.toolbar.apply {
                //Establecemos el título por programación
                title = getString(R.string.fragment_detail_title)

                //Inflamos|Añadimos el menú por programación.
                inflateMenu(R.menu.menu_detail)

                //Añadimos el icono por programación
                setNavigationIcon(R.drawable.ic_close)

                //Añadimos una acción al botón de navegación
                setNavigationOnClickListener {
                    findNavController().navigateUp()
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
        setupObservers()
        viewModel.loadSuperHeroe(args.subjectId)
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
                showError(this)
                binding.name1.hide()
                binding.name2.hide()
                binding.name3.hide()
            }

            it.superHeroe?.apply {
                bind(this)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner,observer)
    }

    private fun showLoading() {
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading() {
        binding.skeletonLayout.showOriginal()
    }

    private fun showError(error: ErrorUiModel) {
        binding.errorView.render(error)
    }

    private fun bind(superHeroe: SuperHeroe) {
        binding.apply {
            imageSuperHeroeDetail.setUrl(superHeroe.principalData.imageUrl[0])
            nameSuperHeroeDetail.text = superHeroe.principalData.name
            stateSuperHeroeDetail.text = superHeroe.biography.alignment
            descriptionSuperHeroeDetail.text = superHeroe.biography.fullName
            attributesSuperHeroeDetail.findViewById<ConstraintLayout>(R.id.intelligence)
                .findViewById<TextView>(R.id.number1).text = superHeroe.principalData.stats[0]
            attributesSuperHeroeDetail.findViewById<ConstraintLayout>(R.id.speed)
                .findViewById<TextView>(R.id.number2).text = superHeroe.principalData.stats[1]
            attributesSuperHeroeDetail.findViewById<ConstraintLayout>(R.id.combat)
                .findViewById<TextView>(R.id.number3).text = superHeroe.principalData.stats[2]
        }
        superHeroeDetailAdapter.submitList(superHeroe.principalData.imageUrl )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}