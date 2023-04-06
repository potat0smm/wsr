package com.example.wsr.CreateUser

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.Api.createUser
import com.example.wsr.Api.model.SignInViewModel
import com.example.wsr.R
import com.example.wsr.databinding.FragmentCreateUserBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CreateUser : Fragment() {

    private var _binding: FragmentCreateUserBinding? =null
    private val binding get() = _binding!!
    private val apiService = RetrofitClient.apiService
    private val items = arrayOf("Мужской","Женский")
    private lateinit var signInViewModel: SignInViewModel

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateUserBinding.inflate(inflater, container, false)
        signInViewModel = ViewModelProvider(requireActivity()).get(SignInViewModel::class.java)
       binding.goInMenu.setOnClickListener {
            findNavController().navigate(R.id.action_createUser_to_mainFragment)
       }

        val maleFemale = resources.getStringArray(R.array.MaleFemale)
        val arrayAdapter = ArrayAdapter(requireContext(), com.google.android.material.R.id.dropdown_menu, maleFemale)
        binding.autoComplete.setAdapter(arrayAdapter)

        val items = listOf("Мужской", "Женский")
        val autoComplete: AutoCompleteTextView = binding.autoComplete
        val adapter = ArrayAdapter(requireContext(), R.layout.item_for_auto_complete, items)

        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                adapterView.getItemAtPosition(i)
            }


        binding.add.setOnClickListener {
            val action = CreateUserDirections.actionCreateUserToMainFragment()
            findNavController().navigate(action)
            navigationToNextFragment()
        }

        navigationToNextFragment()
        return binding.root
    }
    private fun navigationToNextFragment() {
        val id = 0
        val firstname = binding.editName.text.toString()
        val middlename = binding.editPatronymic.text.toString()
        val lastname = binding.editSurname.text.toString()
        val bith = binding.editToBirthday.text.toString()
        val pol = binding.autoComplete.text.chars()
        GlobalScope.launch {
            apiService.createUser(firstname,lastname,middlename,pol,bith,id).enqueue(object : Callback<createUser>{
                override fun onResponse(call: Call<createUser>, response: Response<createUser>) {
                    if (response.isSuccessful){
                        val id = response.body()?.id?:""
                        val bith = response.body()?.bith?:""
                        val firstname = response.body()?.firstname?:""
                        val lastname = response.body()?.lastname?:""
                        val middlename = response.body()?.middlename?:""
                        val pol = response.body()?.pol?:""
                        signInViewModel.saveUser(id as Int,bith,firstname,lastname,pol,middlename)
                        Toast.makeText(requireContext(),"получилось отправить",Toast.LENGTH_SHORT).show()
                    }else{
                        Log.e("CreateUser", "Error creating user: ${response.code()} - ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<createUser>, t: Throwable) {
                    Log.e("CreateUser", "Error creating user: ${t.message}")
                }
            })
        }
    }
        override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}