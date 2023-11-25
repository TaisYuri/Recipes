package com.example.recipes.presentation.morerecipes

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.databinding.FragmentMoreRecipesBinding
import com.example.recipes.presentation.morerecipes.adapter.MoreRecipesListAdapter
import org.koin.androidx.navigation.koinNavGraphViewModel

class MoreRecipesFragment : Fragment() {

    private val viewModel by koinNavGraphViewModel<MoreRecipesViewModel>(R.id.nav_graph)
    private lateinit var binding: FragmentMoreRecipesBinding
    private lateinit var adapter: MoreRecipesListAdapter
    private var currentPage: Int = 1

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

        binding.progress.visibility = View.GONE

        //CustomView
        binding.customFollowers.setCounter("50k")
        binding.customFollowers.setIndicator("Teste teste")
        binding.customFollowers.setBold(false)

        observer()
        loadRecycler()

        return binding.root
    }

    private fun observer() {
        /*viewModel.recipesList.observe(viewLifecycleOwner){
            it?.let{
               adapter.updateList(it)
            }
        }*/
        viewModel.showList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateList2(it)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) binding.progress.visibility = View.VISIBLE
            else binding.progress.visibility = View.GONE
        }
    }


    private fun loadRecycler() {
        binding.recyclerMoreRecipes.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager: LinearLayoutManager =
                        binding.recyclerMoreRecipes.layoutManager as LinearLayoutManager

                    val visibleItemCount: Int = layoutManager.childCount    //Items visiveis
                    val totalItemCount: Int = layoutManager.itemCount  //Total de items no adapter
                    val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()  //Quantidade de items que foram escrolados para fora da tela (da posiçao inicial)

                    if (!isLoading() && !isLastPage()) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                            && firstVisibleItemPosition >= 0
                        ) {
                            currentPage += 1;
                            loadMoreItems()
                        }
                    }
                }
            }
        )
    }

    private fun isLoading(): Boolean {
        return viewModel.isLoading()
    }

    private fun isLastPage(): Boolean {
        return viewModel.isLastPage()
    }

    private fun loadMoreItems() {
        viewModel.loadMoreItems()
    }

}