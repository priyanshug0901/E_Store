package com.android.e_mart.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.e_mart.R
import com.android.e_mart.adapters.HomeItemAdapter
import com.android.e_mart.databinding.HomeFragmentBinding
import com.android.e_mart.model.Products
import com.android.e_mart.ui.viewModels.HomeVieModel
import com.android.e_mart.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: HomeFragmentBinding
    private val homeVieModel by viewModels<HomeVieModel>()

    lateinit var homeItemAdapter: HomeItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment,container,false )
        homeItemAdapter = HomeItemAdapter(::setNavigationCLickListener)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setuprecyclerview

        binding.homeFragmentRecyclerView.apply {
            adapter = homeItemAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        // setupObserver
        homeVieModel.productLiveData.observe(viewLifecycleOwner, Observer {
            resource ->

            when(resource){
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

        })
    }

    fun setNavigationCLickListener(products: Products) {
        //code
        val bundle = Bundle()
        bundle.putSerializable("products", products)
        findNavController().navigate(R.id.action_homeFragment_to_itemDetailFragment, bundle)
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
}