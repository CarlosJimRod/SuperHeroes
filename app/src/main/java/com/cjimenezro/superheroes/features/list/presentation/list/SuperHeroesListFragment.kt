package com.cjimenezro.superheroes.features.list.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjimenezro.superheroes.R
import com.cjimenezro.superheroes.app.presentation.error.ErrorUiModel
import com.cjimenezro.superheroes.databinding.FragmentSuperHeroeBinding
import com.cjimenezro.superheroes.features.list.MainActivity
import com.cjimenezro.superheroes.features.list.domain.SuperHeroe
import com.cjimenezro.superheroes.features.list.presentation.list.adapter.SuperHeroesListAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperHeroesListFragment : Fragment() {

    private var _binding: FragmentSuperHeroeBinding? = null
    private val binding get() = _binding!!

    private lateinit var skeleton: Skeleton

    val viewModel by viewModels<SuperHeroesListViewModel>()

    private val superHeroApadter = SuperHeroesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperHeroeBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            list.layoutManager = LinearLayoutManager(
                this@SuperHeroesListFragment.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            superHeroApadter.setEvent {
                findNavController().navigate(
                    SuperHeroesListFragmentDirections.actionFromFragmentListToFragmentDetail(it.toString())
                )
            }
            list.adapter = superHeroApadter
            layoutList.toolbar.apply {

                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }

                //Establecemos el título por programación
                title = getString(R.string.fragment_list_title)

                //Inflamos|Añadimos el menú por programación.
                inflateMenu(R.menu.menu_list)

                //Añadimos los eventos a los botones del menú
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_favorite -> {
                            //Ejecutamos una acción
                            showFavoriteItems()
                            //Siempre hay que devolver true para que quede pulsado
                            true
                        }

                        R.id.action_delete -> {
                            deleteItems()
                            //Siempre hay que devolver true para que quede pulsado
                            true
                        }

                        else -> false
                    }
                }
            }
        }
    }

    private fun showFavoriteItems() {
        // Hacer algo
    }

    private fun deleteItems() {
        // Hacer algo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        skeleton = binding.list.applySkeleton(R.layout.view_super_heore_item, 8)
        viewModel.loadSuperHeroe()
    }

    private fun executeFunctionInActivity() {
        (activity as MainActivity).showMessage()
    }

    private fun setupObservers() {
        val observer = Observer<SuperHeroesListViewModel.UiState> {
            if (it.isLoading) {
                Snackbar.make(binding.root, "Cargando ...", Snackbar.LENGTH_SHORT).show()
                showLoading()
            } else {
                hideLoading()
            }
            it.errorApp?.apply {
                showError(this)
            }

            it.superHeroe?.apply {
                bind(this)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun showLoading() {
        skeleton.showSkeleton()
    }

    private fun hideLoading() {
        skeleton.showOriginal()
    }

    private fun showError(error: ErrorUiModel) {
        binding.errorView.render(error)
    }

    private fun bind(superHeroes: List<SuperHeroe>) {
        superHeroApadter.submitList(superHeroes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}