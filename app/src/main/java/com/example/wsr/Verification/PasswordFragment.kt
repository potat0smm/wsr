package com.example.wsr.Verification

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.example.wsr.databinding.FragmentPasswordBinding


@Suppress("DEPRECATION")
class PasswordFragment : Fragment() {


    private lateinit var edit1:EditText
    private lateinit var edit2:EditText
    private lateinit var edit3:EditText
    private lateinit var edit4:EditText

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

        edit1 = binding.etPassword1
        edit2 = binding.etPassword2
        edit3 = binding.etPassword3
        edit4 = binding.etPassword4

        setEditTextListener(edit1)
        setEditTextListener(edit2)
        setEditTextListener(edit3)
        setEditTextListener(edit4)

        return binding.root
    }

    private fun setEditTextListener(editText: EditText){
        editText.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if(s.isNullOrEmpty()){
                    editText.background.clearColorFilter()
                }
                else{
                    editText.background.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP)
                }
                checkAllEditTextFilled()
            }

        })
    }

    private fun checkAllEditTextFilled() {
        if (edit1.text.isNotEmpty()&&edit2.text.isNotEmpty()&&edit3.text.isNotEmpty()&&edit4.text.isNotEmpty()){
            findNavController().navigate(R.id.action_passwordFragment_to_createUser)
        }
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
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setEditTextColor(etPassword2, s?.isDigitsOnly() == true)
                if (s?.length == 1) {
                    etPassword3.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        etPassword3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setEditTextColor(etPassword3, s?.isDigitsOnly() == true)
                if (s?.length == 1) {
                    etPassword4.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        etPassword4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setEditTextColor(etPassword4, s?.isDigitsOnly() == true)
                if (s?.length == 1) {
                    hideKeyboard()
                    // If all fields are filled, navigate to next fragment
                    if (etPassword1.text.isNotEmpty() && etPassword2.text.isNotEmpty() &&
                        etPassword3.text.isNotEmpty() && etPassword4.text.isNotEmpty()) {
                        navigateToNextFragment()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Set background and text size for EditText fields
        etPassword1.setBackgroundResource(R.drawable.edit_text_rounded_background)
        etPassword1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        etPassword2.setBackgroundResource(R.drawable.edit_text_rounded_background)
        etPassword2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        etPassword3.setBackgroundResource(R.drawable.edit_text_rounded_background)
        etPassword3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        etPassword4.setBackgroundResource(R.drawable.edit_text_rounded_background)
        etPassword4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f






                // еще не заполненные поля, если введена цифра
                for (j in i + 1 until passwordEditTexts.size) {
                    if (passwordEditTexts[j].text.toString().isEmpty()) {
                        passwordEditTexts[j].requestFocus()
                        break
                    }
                }

                // Возвращаем false, чтобы не показывать клавиатуру
                return@setOnKeyListener true
            }

            // Обрабатываем событие удаления символа
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                // Если поле пустое, то фокус переходит на предыдущее поле
                if (passwordEditText.text.toString().isEmpty()) {
                    passwordEditText.clearFocus()
                    if (i > 0) {
                        passwordEditTexts[i - 1].requestFocus()
                    }
                }

                // Изменяем цвет фона на белый
                passwordEditText.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.white
                    )
                )

                // Возвращаем false, чтобы не показывать клавиатуру
                return@setOnKeyListener true
            }

            // Возвращаем false, чтобы показывать клавиатуру в остальных случаях
            return@setOnKeyListener false
        }
    }
}
}

// Функция для установки цвета текста в EditText в зависимости от условия
private fun setEditTextColor(editText: EditText, condition: Boolean) {
if (condition) {
editText.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
} else {
editText.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
}
}




 */