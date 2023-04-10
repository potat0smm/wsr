package com.example.wsr.Finish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.example.wsr.databinding.FragmentChekBinding

class CheckFragment : Fragment() {

   private var _binding: com.example.wsr.databinding.FragmentChekBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChekBinding.inflate(layoutInflater,container,false)

        binding.backInMenu.setOnClickListener {
            //findNavController().navigate()
        }


        return binding.root
    }
}