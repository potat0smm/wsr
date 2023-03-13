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
        return binding.root
    }




    /*private fun initFocus(){
        binding.OTP1.isEnabled = true

        binding.OTP1.postDelayed({
            binding.OTP1.requestFocus()
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(binding.OTP1,InputMethodManager.SHOW_FORCED)
        },500)
    }
    private fun reset(){
        binding.OTP1.isEnabled = false
        binding.OTP2.isEnabled = false
        binding.OTP4.isEnabled = false
        binding.OTP3.isEnabled = false

        binding.OTP1.setText("")
        binding.OTP2.setText("")
        binding.OTP4.setText("")
        binding.OTP3.setText("")

        initFocus()
    }

    private fun setTextChangeListenner(fromEditText: EditText,targerEditText: EditText? = null, done: (()->Unit)? = null){

        fromEditText.addTextChangedListener{
            it?.let { string ->
                if (string.isNotEmpty()){
                    targerEditText?.let{editText ->
                        editText.isEnabled = true
                        editText.requestFocus()
                    }?:run{
                        done?.let {done ->
                            done()
                        }
                    }
                    fromEditText.clearFocus()
                    fromEditText.isEnabled = false
                }
            }
        }

    }
    private fun setKeyListener(fromEditText: EditText, backToEdit: EditText){
        fromEditText.setOnClickListener { _, _, event ->

            if(null != event && KeyEvent.KEYCODE_DEL == event.keykode){
                backToEdit.isEnabled = true
                backToEdit.requestFocus()
                backToEdit.setText("")

                fromEditText.clearFocus()
                fromEditText.isEnabled = false
            }
            false

        }
    }

    private fun getSystemService(inputMethodService: String): Any {
        Context.INPUT_METHOD_SERVICE.toString()
        return getSystemService(inputMethodService)
    }*/
}

