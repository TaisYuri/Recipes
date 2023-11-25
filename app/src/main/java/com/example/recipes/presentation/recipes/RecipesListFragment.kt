package com.example.recipes.presentation.recipes


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.databinding.FragmentRecipesListBinding
import com.example.recipes.presentation.recipes.adapter.ItemListener
import com.example.recipes.presentation.recipes.adapter.ListAdapter
import com.example.recipes.presentation.recipes.adapter.ListChoiceAdapter
import org.koin.androidx.navigation.koinNavGraphViewModel


class RecipesListFragment : Fragment(), ItemListener {

    private val viewModel by koinNavGraphViewModel<RecipesListViewModel>(R.id.nav_graph)
    private var _binding: FragmentRecipesListBinding? = null
    private lateinit var adapter: ListAdapter
    private lateinit var adapterChoice: ListChoiceAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecipesListBinding.inflate(inflater, container, false)

        adapter = ListAdapter(this)
        binding.recycler.apply {
            this.adapter = this@RecipesListFragment.adapter
            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        adapterChoice = ListChoiceAdapter(this)
        binding.recycler2.apply {
            this.adapter = this@RecipesListFragment.adapterChoice
            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        binding.btnList?.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_moreRecipesFragment)
        }

        //CustomView
        binding.customProfileImage?.setImageProfile(R.drawable.irineu)
        binding.customProfileImage?.setVisualized(0)

        clickedButtonChoice()
        observer()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer() {
        viewModel.recipesList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateList(it)
            }
        }

        viewModel.recipesList2.observe(viewLifecycleOwner) {
            it?.let {
                adapterChoice.updateList(it)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            with(binding) {
                progressLoading?.visibility = if (it) VISIBLE else GONE
                recycler2.visibility = if (it) GONE else VISIBLE
            }
        }
    }

    private fun clickedButtonChoice() {
        val buttonSalad = binding.buttonChoiceSalad
        val buttonVeg = binding.buttonChoiceVegetarian
        val buttonDessert = binding.buttonChoiceDessert

        buttonVeg?.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.onSelected("vegetarian")
        }

        buttonSalad.setOnClickListener {
            viewModel.onSelected("salad")
        }

        buttonDessert.setOnClickListener {
            viewModel.onSelected("dessert")
        }
    }

    override fun onItemSelected(position: Int) {
        TODO("Not yet implemented")
    }

}