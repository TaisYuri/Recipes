package com.example.recipes.presentation.morerecipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.R
import com.example.recipes.databinding.FragmentMoreRecipesBinding
import com.example.recipes.presentation.morerecipes.adapter.MoreRecipesListAdapter
import org.koin.androidx.navigation.koinNavGraphViewModel

class MoreRecipesFragment: Fragment(){

    private val viewModel by koinNavGraphViewModel<MoreRecipesViewModel>(R.id.nav_graph)
    private lateinit var binding: FragmentMoreRecipesBinding
    private lateinit var adapter: MoreRecipesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMoreRecipesBinding.inflate(inflater, container, false)

        adapter = MoreRecipesListAdapter()
        binding.recyclerMoreRecipes.apply {
            this.adapter = this@MoreRecipesFragment.adapter
            this.layoutManager = LinearLayoutManager(context)
        }


        observer()
        loadRecycler()

        return binding.root
    }

    private fun observer(){
        viewModel.recipesList.observe(viewLifecycleOwner){
            it?.let{
               adapter.updateList(it)
            }


        }
    }

    private fun loadRecycler(){
    }

}