package com.example.recipes.presentation.supermarketproducts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.recipes.R
import com.example.recipes.databinding.FragmentSupermarketProductsBinding
import com.example.recipes.presentation.recipes.RecipesListViewModel

class SupermarketProductsFragment : Fragment() {

    private lateinit var binding: FragmentSupermarketProductsBinding
    private val viewModel by hiltNavGraphViewModels<SupermarketProductsViewModel>(R.id.nav_graph)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSupermarketProductsBinding.inflate(inflater, container, false)

        observer()
        return binding.root
    }

    private fun observer(){
        viewModel.supermarketProducts.observe(viewLifecycleOwner){
            val s = ""
        }
    }
}