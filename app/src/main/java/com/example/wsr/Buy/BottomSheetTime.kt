package com.example.wsr.Buy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsr.MainMenu.BottomSheet.BottomSheet
import com.example.wsr.R
import com.example.wsr.databinding.FragmentBottomSheetTimeBinding

class BottomSheetTime : BottomSheet() {

    private var _binding:FragmentBottomSheetTimeBinding?=null
    private val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetTimeBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

}