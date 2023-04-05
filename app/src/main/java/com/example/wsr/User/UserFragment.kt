package com.example.wsr.User

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.example.wsr.Api.RetrofitClient
//import com.example.wsr.MainMenu.MenuFragment
import com.example.wsr.R
import com.example.wsr.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val apiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(inflater,container,false)

        binding.addUser.setOnClickListener {

        }

        val male = resources.getStringArray(R.array.MaleFemale)
        val arrayAdapter = ArrayAdapter(requireContext(), com.google.android.material.R.id.dropdown_menu,male)
        binding.autoCompleteUser.setAdapter(arrayAdapter)

        val items = listOf("Мужчина", "Девушка")
        val autoCompleteUser: AutoCompleteTextView = binding.autoCompleteUser
        val adapter = ArrayAdapter(requireContext(),R.layout.item_for_auto_complete,items)
        autoCompleteUser.setAdapter(adapter)

        autoCompleteUser.onItemClickListener = AdapterView.OnItemClickListener{adapterView, view, i, l->
            adapterView.getItemAtPosition(i)
        }
        return binding.root
    }
}