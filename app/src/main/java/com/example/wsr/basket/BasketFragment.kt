package com.example.wsr.basket

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.R
import com.example.wsr.databinding.FragmentBasketBinding
import kotlinx.coroutines.launch

class BasketFragment : Fragment() {

    private lateinit var adapter: AdapterRecyclerViewBasket
    private lateinit var reyclerview: RecyclerView
    private var _binding:FragmentBasketBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiClient = RetrofitClient.apiService
        lifecycleScope.launch {
            val response = apiClient.getCatalog()
            if (response.isSuccessful){
                response.body()
            }else{

            }
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(layoutInflater,container,false)

        binding.goInOrder.setOnClickListener {
            val action = BasketFragmentDirections.actionBasketFragmentToByFragment2()
            findNavController().navigate(action)
        }

        reyclerview = binding.basketRecyclerView
        reyclerview.layoutManager = LinearLayoutManager(requireContext())
        reyclerview.adapter = AdapterRecyclerViewBasket(emptyList())
        return binding.root
    }


}