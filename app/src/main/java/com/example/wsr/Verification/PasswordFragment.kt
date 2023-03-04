package com.example.wsr.Verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.example.wsr.databinding.FragmentPasswordBinding


class PasswordFragment : Fragment() {

    private var _binding:FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPasswordBinding.inflate(layoutInflater,container,false)

        binding.goMenu.setOnClickListener {
            findNavController().navigate(R.id.action_passwordFragment_to_createUser)
        }



        return binding.root
    }


}