package com.example.wsr.MainMenu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.NotificationCompat.getCategory
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.CatalogItem
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.Api.model.SharedViewModel
import com.example.wsr.ManyFragment.MainFragmentDirections
import com.example.wsr.R
import com.example.wsr.databinding.FragmentMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

@SuppressLint("NotifyDataSetChanged")
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var secondMenuAdapter: MenuAdapterSecond
    private lateinit var thirdMenuAdapter: MenuAdapterThird

    private lateinit var recyclerViewFirst: RecyclerView
    private lateinit var recyclerViewSecond: RecyclerView
    private lateinit var recyclerViewThird: RecyclerView

    private lateinit var addBtn: MaterialButton
    private lateinit var FL: FrameLayout
    private lateinit var analysisList: List<CatalogItem>

    //searchView
    private var searchQuery: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)


        // Set up first RecyclerView
        recyclerViewFirst = binding.firstRecyclerView
        recyclerViewFirst.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        menuAdapter = MenuAdapter(emptyList())
        recyclerViewFirst.adapter = menuAdapter

        // Set up second RecyclerView
        recyclerViewSecond = binding.secondRecyclerView
        recyclerViewSecond.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        secondMenuAdapter = MenuAdapterSecond { onItemClick ->
            val catalog = getCategory(onItemClick)
            thirdMenuAdapter.analysisList = catalog
            thirdMenuAdapter.notifyDataSetChanged()
            recyclerViewThird.adapter = thirdMenuAdapter
            // Handle item click
            // Call nigga() function or perform any other action
        }
        recyclerViewSecond.adapter = secondMenuAdapter

        // Set up third RecyclerView
        recyclerViewThird = binding.thirdRecyclerView
        recyclerViewThird.layoutManager = LinearLayoutManager(requireContext())
        addBtn = binding.addBtn
        FL = binding.FM


        // Set up click listener for Add button
        goBasket()

        val searchView = binding.searchview
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchQuery = newText
                filterData(searchQuery)
                return true
            }

            private fun filterData(query: String) {
                val filterList = mutableListOf<CatalogItem>()
                for(item in thirdMenuAdapter.analysisList){
                    if(item.name.contains(query,true)){
                        filterList.add(item)
                    }
                }
                thirdMenuAdapter.analysisList = filterList
                thirdMenuAdapter.notifyDataSetChanged()
            }
        })
        return binding.root
    }


    private fun showBottomSheetDialog(itemId:Int){
        val bottomSheet = BottomSheetDialog(requireContext())
        val bottomSheetView = layoutInflater.inflate(R.layout.fragment_bottom_sheet,null)

        bottomSheet.setContentView(bottomSheetView)
        bottomSheet.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thirdMenuAdapter = MenuAdapterThird(emptyList(),addBtn,FL){ itemId ->
            showBottomSheetDialog(itemId)
            //  val action = MainFragmentDirections.actionMainFragmentToBottomSheet()
            // findNavController().navigate(action)
        }
        recyclerViewThird.adapter = thirdMenuAdapter


        val apiClient = RetrofitClient.apiService
        lifecycleScope.launch {
            val response = apiClient.getCatalog()
            val catalog = response.body()
            if (response.isSuccessful && !catalog.isNullOrEmpty()) {
                thirdMenuAdapter.analysisList = catalog
                thirdMenuAdapter.notifyDataSetChanged()
            } else {
                // Handle error
            }
        }

        val apiClientFirst = RetrofitClient.apiService
        lifecycleScope.launch {
            val responseFirst = apiClientFirst.getNews()
            if (responseFirst.isSuccessful) {
                val catalogFirst = responseFirst.body()
                menuAdapter.menuList = catalogFirst ?: emptyList()
                menuAdapter.notifyDataSetChanged()
            } else {
                // Handle error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goBasket() {

        binding.addBtn.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToBasketFragment()
            findNavController().navigate(action)
        }
    }

    private fun getCategory(category:String): List<CatalogItem>{
        val list = mutableListOf<CatalogItem>()
        val apiClient = RetrofitClient.apiService
        lifecycleScope.launch {
            val response = apiClient.getCatalog()
            val catalog = response.body()
            if (response.isSuccessful && !catalog.isNullOrEmpty()) {

                for (item in catalog) {
                    if (item.category == category){
                        list.add(item)
                    }
                }
                thirdMenuAdapter.analysisList = list
                thirdMenuAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
            }
        }
        return list
    }
}
