package com.example.wsr.Verification

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
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


class CodeFragment : Fragment(R.layout.fragment_code) {



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

        binding.OTP1.addTextChangedListener()
        binding.OTP2.addTextChangedListener()
        binding.OTP4.addTextChangedListener()
        binding.OTP3.addTextChangedListener()


        return binding.root


    }

}

