package com.example.wsr.Verification

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.example.wsr.databinding.FragmentCodeBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


@Suppress("DEPRECATION")
class CodeFragment : Fragment(R.layout.fragment_code) {

    private lateinit var edit1:EditText
    private lateinit var edit2:EditText
    private lateinit var edit3:EditText
    private lateinit var edit4:EditText

    private var _binding: FragmentCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCodeBinding.inflate(layoutInflater, container, false)
        binding.backToEmailFragment.setOnClickListener{
            findNavController().navigate(R.id.action_codeFragment_to_emailFragment)
        }

        binding.goNewPassword.setOnClickListener {
            findNavController().navigate(R.id.action_codeFragment_to_passwordFragment)
        }
        //isInputValid()

        edit1 = binding.OTP1
        edit2 = binding.OTP2
        edit3 = binding.OTP4
        edit4 = binding.OTP3


        setEditTextListener(edit1)
        setEditTextListener(edit2)
        setEditTextListener(edit3)
        setEditTextListener(edit4)



        return binding.root


    }
    private fun setEditTextListener(editText: EditText){
        editText.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if(s.isNullOrEmpty()){
                    editText.background.clearColorFilter()
                }
                else{
                    editText.background.setColorFilter(Color.BLUE,PorterDuff.Mode.SRC_ATOP)
                }
                chekAllEditTextFilled()
            }

        })
    }

    private fun chekAllEditTextFilled() {
        if (edit1.text.isNotEmpty()&&edit2.text.isNotEmpty()&&edit3.text.isNotEmpty()&&edit4.text.isNotEmpty()){
            findNavController().navigate(R.id.action_passwordFragment_to_createUser)
        }
    }


    /* private fun isInputValid(): Boolean{
         val input1 = binding.OTP1.text.toString().trim()
         val input2 = binding.OTP2.text.toString().trim()
         val input3 = binding.OTP4.text.toString().trim()
         val input4 = binding.OTP3.text.toString().trim()

         if(input1.isEmpty()|| input2.isEmpty()|| input3.isEmpty()||input4.isEmpty()){
             return false
         }

         if(!TextUtils.isDigitsOnly(input1)|| !TextUtils.isDigitsOnly(input2)||!TextUtils.isDigitsOnly(input3)|| !TextUtils.isDigitsOnly(input4)){
             return false
         }
         return true

     }*/

}
/*
class VerificationFragment : Fragment() {

    private lateinit var viewModel: VerificationViewModel
    private lateinit var inputLayouts: List<TextInputLayout>
    private lateinit var inputEditTexts: List<TextInputEditText>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_verification, container, false)

        // Получаем ссылки на View
        inputLayouts = listOf(
            view.findViewById(R.id.input_layout_1),
            view.findViewById(R.id.input_layout_2),
            view.findViewById(R.id.input_layout_3),
            view.findViewById(R.id.input_layout_4)
        )

        inputEditTexts = listOf(
            view.findViewById(R.id.input_edit_text_2),
            view.findViewById(R.id.input_edit_text_3),
            view.findViewById(R.id.input_edit_text_4)
        )

        // Получаем ссылку на ViewModel
        viewModel = ViewModelProvider(this).get(VerificationViewModel::class.java)

        // Подписываемся на изменение состояния ViewModel
        viewModel.isValid.observe(viewLifecycleOwner, Observer { isValid ->
            if (isValid) {
                // Верификация пройдена успешно
            } else {
                // Верификация не пройдена
                inputLayouts.forEach { it.error = "Неверный код" }
            }
        })

        // Слушатель изменения текста в EditText'ах
        val textWatcher = VerificationTextWatcher(viewModel)

        // Назначаем слушатель для каждого EditText'а
        inputEditTexts.forEachIndexed { index, editText ->
            editText.addTextChangedListener(textWatcher)

            // Назначаем фокус на первый EditText
            if (index == 0) editText.requestFocus()
        }

        return view
    }

}
 */
