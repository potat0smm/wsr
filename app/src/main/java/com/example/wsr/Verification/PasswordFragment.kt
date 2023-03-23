package com.example.wsr.Verification


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.example.wsr.databinding.FragmentPasswordBinding


@Suppress("DEPRECATION")
class PasswordFragment : Fragment() {

    private lateinit var pinText: List<TextView>
    private lateinit var pinCircles: List<View>
    private lateinit var del:ImageView
    private var pinCode: String = ""

    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPasswordBinding.inflate(layoutInflater, container, false)
        // пропустить
        binding.skipped.setOnClickListener {
            val action = PasswordFragmentDirections.actionPasswordFragmentToCreateUser()
            findNavController().navigate(action)
        }
        // кнопки
        pinText = listOf(
            binding.pin1,
            binding.pin2,
            binding.pin3,
            binding.pin4,
            binding.pin5,
            binding.pin6,
            binding.pin7,
            binding.pin8,
            binding.pin9,
            binding.pin0
        )
        //изображения в форме кружочков
        pinCircles = listOf(
            binding.circle1,
            binding.circle2,
            binding.circle3,
            binding.circle4
        )
        //изображение (удаление)
        del = binding.pinDel
        del.setOnClickListener {
            if (pinCode.isNotEmpty()) {
                pinCode = pinCode.dropLast(1)
                if (pinCode.length < pinCircles.size) {
                    pinCircles[pinCode.length].setBackgroundResource(R.drawable.circle_pass)
                }
            }
        }
        return binding.root
    }

    private fun navigateToCreateUserFragment() {
        val action = PasswordFragmentDirections.actionPasswordFragmentToCreateUser()
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupKeyboardListeners()
    }
    private fun setupKeyboardListeners() {
        pinText.forEach { textView ->
            textView.setOnClickListener {
                val value = textView.text.toString()
                onPinCodeButtonClick(value)
            }
        }
    }

    private fun onPinCodeButtonClick(value: String) {
        when(value){
        "Del" ->{
            if (pinCode.isNotEmpty()){
                pinCode = pinCode.dropLast(1)
                updatePinCircles()
            }
        }
            "OK"->{
                if (pinCode.isNotEmpty()){
                    val action = PasswordFragmentDirections.actionPasswordFragmentToCreateUser()
                    findNavController().navigate(action)
                }
            }
            else ->{
                if(pinCode.length < pinCircles.size){
                    pinCode+=value
                    updatePinCircles()
                }
            }
        }
    }

    private fun updatePinCircles() {
        pinCircles.forEachIndexed { index, view ->
            view.setBackgroundResource(if (index < pinCode.length) R.drawable.circle_pass_two else R.drawable.circle_pass) }
    }
}


