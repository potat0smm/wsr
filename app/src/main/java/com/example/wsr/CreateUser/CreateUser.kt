package com.example.wsr.CreateUser

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.wsr.R
import com.example.wsr.databinding.FragmentCreateUserBinding


class CreateUser : Fragment() {

    private var _binding: FragmentCreateUserBinding? =null
    private val binding get() = _binding!!

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateUserBinding.inflate(inflater, container, false)

        val maleFemale = resources.getStringArray(R.array.MaleFemale)
        val arrayAddapter = ArrayAdapter(requireContext(), com.google.android.material.R.id.dropdown_menu,maleFemale)
        binding.autoComplete.setAdapter(arrayAddapter)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}