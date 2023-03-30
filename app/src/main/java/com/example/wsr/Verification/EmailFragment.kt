package com.example.wsr.Verification

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wsr.Api.CodeResponse
import com.example.wsr.Api.Email
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.R
import com.example.wsr.databinding.FragmentEmailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmailFragment : Fragment() {

    private val apiService = RetrofitClient.apiService
    private var _binding: FragmentEmailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentEmailBinding.inflate(layoutInflater,container,false)
        // авторизация
        signUp()
        sendCode()
        return binding.root
    }
    private fun validateForm(): Boolean {
        //Иконка
        val icon = AppCompatResources.getDrawable(requireContext(),R.drawable.baseline_warning_24)

        icon?.setBounds(0,0,icon.intrinsicWidth,icon.intrinsicHeight)
        when{
            TextUtils.isEmpty(binding.editText.text.toString().trim())->{
                binding.editText.setError("Please Enter Email",icon)
            }
            binding.editText.text.toString().isNotEmpty()->{
                //регулярка
                if (binding.editText.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    return true
                }else{
                    binding.editText.setError("Please Valid",icon)
                }
            }
        }
        return false
    }
    private fun sendCode(){
        val email = binding.editText.text.toString()
        //асинхронный запрос sendCode() с библиотекой RETROFIT, отправляет email на сервер чтоб получить код поддтверждения
        apiService.sendCode(email).enqueue(object : Callback<CodeResponse> {
            override fun onResponse(call: Call<CodeResponse>, response: Response<CodeResponse>) {
                //если 200, то получаем код и передаем его в CodeFragment (email и code)
                if (response.isSuccessful) {
                    val code = response.body()?.code
                    val codeFragment = CodeFragment.newInstance(email, code)
                    parentFragmentManager.beginTransaction()
                        //если код отправлен, то переход с этими данными в другой фрагмент
                        .replace(R.id.fragmentContainerView, codeFragment)
                        .addToBackStack(null)
                        //завершение
                        .commit()
                    // в случае успешной отправки
                    Toast.makeText(requireContext(), response.body()?.message, Toast.LENGTH_SHORT).show()

                }else{
                    //в случае огшибки
                    Toast.makeText(requireContext(),"error", Toast.LENGTH_SHORT).show()
                }
            }
            //ошибка
            override fun onFailure(call: Call<CodeResponse>, t: Throwable) {

            }
        })
    }
    private fun signUp() {
        binding.materialButton.isEnabled = false
        binding.editText.addTextChangedListener {
            val email = binding.editText.text.toString()
            var status = false
            //проверка что поле не пустое
            if (email.isNotEmpty()) {
                //если емаил верен регуляркам
                if (validateForm()) {
                    status = true
                    //отправка кода, если соотвествует условиям выше
                }
            }
            binding.materialButton.isEnabled = status
            //переход в другой фрагмент
            binding.materialButton.setOnClickListener {
                val action = EmailFragmentDirections.actionEmailFragmentToCodeFragment()
                findNavController().navigate(action)
            }
        }
    }
    //чтоб предотвратить утечку памяти
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}