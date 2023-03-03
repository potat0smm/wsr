package com.example.wsr.Verification

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.example.wsr.databinding.FragmentCodeBinding


@Suppress("DEPRECATION")
class CodeFragment : Fragment() {

    private var _binding: FragmentCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentCodeBinding.inflate(layoutInflater)
        binding.root

        binding.OTP1.isEnabled = true

        binding.OTP1.postDelayed({
            binding.OTP1.requestFocus()
        },500)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_code, container, false)

        view.findViewById<ImageView>(R.id.back_to_email_fragment).setOnClickListener{
            findNavController().navigate(R.id.action_codeFragment_to_emailFragment)
        }

        var OTP1 = view.findViewById<EditText>(R.id.OTP1)

        return view
    }
}

