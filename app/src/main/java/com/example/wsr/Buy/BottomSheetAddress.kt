package com.example.wsr.Buy

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsr.MainMenu.BottomSheet.BottomSheet
import com.example.wsr.R
import com.example.wsr.databinding.FragmentBottomSheetAddressBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class BottomSheetAddress : BottomSheet() {

    private var _binding: FragmentBottomSheetAddressBinding?=null
    private val binding get() = _binding!!
    private lateinit var bottomSheet: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetAddressBinding.inflate(layoutInflater,container,false)
        bottomSheet = BottomSheetDialog(requireContext(),R.style.CustomBottomSheetBack)

        return binding.root

    }

}