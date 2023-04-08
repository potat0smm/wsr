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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.Api.model.SharedViewModel
import com.example.wsr.ManyFragment.MainFragmentDirections
import com.example.wsr.databinding.FragmentMenuBinding
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
        recyclerViewSecond.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        secondMenuAdapter = MenuAdapterSecond { onItemClick ->
            // Handle item click
            // Call nigga() function or perform any other action
            nigga()
        }
        recyclerViewSecond.adapter = secondMenuAdapter

        // Set up third RecyclerView
        recyclerViewThird = binding.thirdRecyclerView
        recyclerViewThird.layoutManager = LinearLayoutManager(requireContext())
        addBtn = binding.addBtn
        FL = binding.FM
        thirdMenuAdapter = MenuAdapterThird(emptyList(), addBtn, FL)
        recyclerViewThird.adapter = thirdMenuAdapter

        // Set up click listener for Add button
        goBasket()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun nigga() {
        // Perform action on item click
    }

}

/*
class MenuFragment : Fragment() {

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var secondMenuAdapter: MenuAdapterSecond
    private lateinit var recyclerView: RecyclerView
    private lateinit var thirdMenuAdapter: MenuAdapterThird
    private lateinit var addBtn: MaterialButton // добавьте это поле
    private lateinit var FL: FrameLayout
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(layoutInflater,container,false)
        //GoBasket
        goBasket()
        //third recyclerview
        recyclerView = binding.thirdRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        addBtn = binding.addBtn
        FL = binding.FM
        thirdMenuAdapter = MenuAdapterThird(emptyList(),addBtn,FL)
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

    private fun goBasket(){
        binding.addBtn.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToBasketFragment()
            findNavController().navigate(action)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
 */
/*
  @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(layoutInflater,container,false)
        //GoBasket
        goBasket()
        //third recyclerview
        recyclerView = binding.thirdRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        addBtn = binding.addBtn
        FL = binding.FM
        thirdMenuAdapter = MenuAdapterThird(emptyList(),addBtn,FL)
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

    private fun goBasket(){
        binding.addBtn.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToBasketFragment()
            findNavController().navigate(action)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
 */

