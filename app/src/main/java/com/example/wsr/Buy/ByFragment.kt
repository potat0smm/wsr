package com.example.wsr.Buy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.example.wsr.databinding.FragmentByBinding


class ByFragment : Fragment() {
    private var _binding: FragmentByBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentByBinding.inflate(layoutInflater,container,false)

        val male = resources.getStringArray(R.array.MaleFemale)
        val arrayAdapter = ArrayAdapter(requireContext(), com.google.android.material.R.id.dropdown_menu,male)
        binding.maleFemale.setAdapter(arrayAdapter)
        val items = listOf("Тицкий Эдуард","Тицкая Елена")
        val autoComplete:AutoCompleteTextView = binding.maleFemale
        val adapter = ArrayAdapter(requireContext(),R.layout.item_for_auto_complete,items)
        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{adapterView, view, i, l ->
            adapterView.getItemAtPosition(i)
        }
        //Чтоб при нажатии на editText он сразу открывался и не было курсора
        val address = binding.addressEdit
        address.clearFocus()
        address.isCursorVisible = false
        //binding.editTime
        binding.goFinish.setOnClickListener {
            val action = ByFragmentDirections.actionByFragment2ToFinishFragment()
            findNavController().navigate(action)
        }
        binding.addressEdit.setOnClickListener{

            val action = ByFragmentDirections.actionByFragment2ToBottomSheetAddress()
            findNavController().navigate(action)
        }
        return binding.root
    }
}