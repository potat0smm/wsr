package com.example.wsr.Buy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.example.wsr.databinding.FragmentByBinding


class ByFragment : Fragment() {
    private var _binding: FragmentByBinding?=null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentByBinding.inflate(layoutInflater,container,false)

        binding.goFinish.setOnClickListener {
            val action = ByFragmentDirections.actionByFragment2ToFinishFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

}