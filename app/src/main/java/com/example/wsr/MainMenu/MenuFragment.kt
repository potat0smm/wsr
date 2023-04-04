package com.example.wsr.MainMenu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.ManyFragment.MainFragmentDirections
import com.example.wsr.databinding.FragmentMenuBinding
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

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
