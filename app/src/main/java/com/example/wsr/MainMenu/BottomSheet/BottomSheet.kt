package com.example.wsr.MainMenu.BottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


open class BottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var  recyclerview: RecyclerView
    private lateinit var menuAdapter: AdapterBottomSheet
    private val apiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val itemId = arguments?.getInt("id",-1)
        if (itemId!=null && itemId!=-1){

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)


        binding.saveInBasket.setOnClickListener {
           // findNavController().popBackStack(R.id.menuFragment2,false)
            //findNavController().navigate(R.id.action_bottomSheet_to_bottomSheetGoBasket)
        }
        binding.close.setOnClickListener{
            findNavController().popBackStack()
        }
        return binding.root
    }
}