package com.example.wsr.MainMenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.wsr.Api.CatalogItem
import com.example.wsr.Api.NewsItem
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.databinding.FragmentMenuBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module



class MenuFragment : Fragment() {

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var secondMenuAdapter: MenuAdapterSecond
    private lateinit var recyclerView: RecyclerView
    private lateinit var thirdMenuAdapter: MenuAdapterThird

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(layoutInflater,container,false)
        //third recyclerview
        recyclerView = binding.thirdRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        thirdMenuAdapter = MenuAdapterThird(emptyList())
        recyclerView.adapter = thirdMenuAdapter
        //first recyclerview
        recyclerView = binding.firstRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        menuAdapter = MenuAdapter(emptyList())
        recyclerView.adapter = menuAdapter
        //second recyclerview
        recyclerView = binding.secondRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        secondMenuAdapter = MenuAdapterSecond(emptyList())
        recyclerView.adapter = secondMenuAdapter

        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiClient = RetrofitClient.apiService
        lifecycleScope.launch {
            val response = apiClient.getCatalog()
            if(response.isSuccessful){
                val catalog = response.body()
                thirdMenuAdapter.analysisList = catalog?: emptyList()
                thirdMenuAdapter.notifyDataSetChanged()
            }else{
                //error
            }
        }
        val apiClientFirst = RetrofitClient.apiService
        lifecycleScope.launch {
            val responseFirst = apiClientFirst.getNews()
            if (responseFirst.isSuccessful){
                val catalogFirst = responseFirst.body()
                menuAdapter.menuList = catalogFirst?: emptyList()
                menuAdapter.notifyDataSetChanged()
            }else{
                //er
            }
        }
        val apiClientSecond = RetrofitClient.apiService
        lifecycleScope.launch{
            val responseSecond = apiClientSecond.getCatalog()
            if (responseSecond.isSuccessful){
                val catalogSecond = responseSecond.body()
                secondMenuAdapter.menuSecondList = catalogSecond?: emptyList()
                secondMenuAdapter.notifyDataSetChanged()
            }else{
              //error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
