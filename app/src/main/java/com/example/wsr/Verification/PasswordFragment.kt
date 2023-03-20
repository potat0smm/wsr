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
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.example.wsr.databinding.FragmentPasswordBinding


@Suppress("DEPRECATION")
class PasswordFragment : Fragment() {


    private lateinit var edit1: EditText
    private lateinit var edit2: EditText
    private lateinit var edit3: EditText
    private lateinit var edit4: EditText

    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPasswordBinding.inflate(layoutInflater, container, false)

        binding.goMenu.setOnClickListener {
            findNavController().navigate(R.id.action_passwordFragment_to_createUser)
        }

        edit1 = binding.etPassword1
        edit2 = binding.etPassword2
        edit3 = binding.etPassword3
        edit4 = binding.etPassword4

        setUpEditTexts()

        return binding.root
    }

    private fun setUpEditTexts() {
        edit1.addTextChangedListener(GenericTextWatcher(edit1,edit2))
        edit2.addTextChangedListener(GenericTextWatcher(edit2,edit3))
        edit3.addTextChangedListener(GenericTextWatcher(edit3,edit4))
        edit4.addTextChangedListener(GenericTextWatcher(edit4,null))
    }

    private inner class GenericTextWatcher(
        private val currentView:View,
        private val nextView:View?
    ):TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            if (text.length == 1 && nextView != null) {
                nextView.requestFocus()
            }
            currentView.setBackgroundColor(R.drawable.password_back_ground)
            if (edit1.text.toString().isNotEmpty() &&
                edit2.text.toString().isNotEmpty() &&
                edit3.text.toString().isNotEmpty() &&
                edit4.text.toString().isNotEmpty()
            )
                findNavController().navigate(R.id.action_passwordFragment_to_createUser)
        }
    }
}
