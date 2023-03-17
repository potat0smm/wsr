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
/*
class PasswordFragment : Fragment() {

    private lateinit var etPassword1: EditText
    private lateinit var etPassword2: EditText
    private lateinit var etPassword3: EditText
    private lateinit var etPassword4: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_password, container, false)

        etPassword1 = view.findViewById(R.id.et_password_1)
        etPassword2 = view.findViewById(R.id.et_password_2)
        etPassword3 = view.findViewById(R.id.et_password_3)
        etPassword4 = view.findViewById(R.id.et_password_4)

        etPassword1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setEditTextColor(etPassword1, s?.isDigitsOnly() == true)
                if (s?.length == 1) {
                    etPassword2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        etPassword2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s



    private fun setupEditTexts() {
        val passwordLength = 4
        val passwordEditTexts = arrayOf(
            binding.editTextPassword1,
            binding.editTextPassword2,
            binding.editTextPassword3,
            binding.editTextPassword4
        )

        for (i in passwordEditTexts.indices) {
            val passwordEditText = passwordEditTexts[i]

            // Устанавливаем круглую форму
            passwordEditText.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.edit_text_rounded_background
            )

            // Устанавливаем маленький размер текста
            passwordEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)

            passwordEditText.setOnKeyListener { _, keyCode, event ->
                // Обрабатываем события ввода цифр
                if (event.action == KeyEvent.ACTION_DOWN && keyCode in 48..57) {
                    // Изменяем цвет фона на голубой, если вводится цифра
                    passwordEditText.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.light_blue
                        )
                    )

                    // Переход к следую




 */