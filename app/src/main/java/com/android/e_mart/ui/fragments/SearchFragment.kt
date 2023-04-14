package com.android.e_mart.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.android.e_mart.R
import com.android.e_mart.adapters.HomeItemAdapter
import com.android.e_mart.databinding.SearchFragmentBinding
import com.android.e_mart.model.Products
import com.android.e_mart.ui.viewModels.HomeVieModel
import com.android.e_mart.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    lateinit var binding: SearchFragmentBinding
    lateinit var homeItemAdapter: HomeItemAdapter
    private val viewModel by viewModels<HomeVieModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var job: Job? = null
        binding.searchBox.addTextChangedListener { text ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)

                text?.let {
                    if (text.toString().isNotEmpty()) {
                        // setupObserver
                       viewModel.getSearchResults(text.toString())
                    }
                }
            }
        }
        setupAdapter(binding)
        setupObserver()


    }

    private fun setupObserver() {
        viewModel.productLiveData.observe(
            viewLifecycleOwner
        ) { resource ->
            when (resource) {
                is Resource.Success -> {
                    hideProgressBar()
                    resource.data?.let {
                        homeItemAdapter.differ.submitList(resource.data.products)
                    }
                }
                is Resource.Error -> {
                    Log.e("####", "Error: getting ${resource.message} Error")
                    Log.d("####", "Error: getting ${resource.message} Error")
                }
                else -> {
                    showProgressBar()
                }
            }

        }
    }
    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupAdapter(binding : SearchFragmentBinding) {
        homeItemAdapter = HomeItemAdapter(::setNavigationCLickListener)
        binding.searchFragmentRecyclerView.apply {
            adapter = homeItemAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL))
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }
    }

    fun setNavigationCLickListener(products: Products) {
        //code
        Toast.makeText(requireContext(), "adapter clicked", Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putSerializable("products", products)
        findNavController().navigate(R.id.action_searchFragment_to_itemDetailFragment, bundle)
    }
}