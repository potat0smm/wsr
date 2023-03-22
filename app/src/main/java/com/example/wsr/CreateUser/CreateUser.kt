package com.example.wsr.CreateUser

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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

        binding.goInMenu.setOnClickListener {
            findNavController().navigate(R.id.action_createUser_to_menuFragment2)
        }

        val maleFemale = resources.getStringArray(R.array.MaleFemale)
        val arrayAdapter = ArrayAdapter(requireContext(), com.google.android.material.R.id.dropdown_menu,maleFemale)
        binding.autoComplete.setAdapter(arrayAdapter)

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_createUser_to_menuFragment2)
        }

        val items = listOf("Мужчина", "Девушка")
        val autoComplete: AutoCompleteTextView = binding.autoComplete
        val adapter = ArrayAdapter(requireContext(),R.layout.item_for_auto_complete,items)

        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            adapterView.getItemAtPosition(i)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}