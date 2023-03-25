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

    private lateinit var pinCircles: List<View>
    private lateinit var del: ImageView
    private var pinCode: String = ""

    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)

        //пропустить
        binding.skipped.setOnClickListener {
            navigateToCreateUserFragment()
        }

        // спискок кружочков
        pinCircles = listOf(
            binding.circle1,
            binding.circle2,
            binding.circle3,
            binding.circle4
        )

        //  кнопка удалить
        del = binding.pinDel
        del.setOnClickListener {
            if (pinCode.isNotEmpty()) {
                pinCode = pinCode.dropLast(1)
                updatePinCircles()
            }
        }

        // нажатие на кнопки
        binding.pin0.setOnClickListener { onPinDigitPressed(0) }
        binding.pin1.setOnClickListener { onPinDigitPressed(1) }
        binding.pin2.setOnClickListener { onPinDigitPressed(2) }
        binding.pin3.setOnClickListener { onPinDigitPressed(3) }
        binding.pin4.setOnClickListener { onPinDigitPressed(4) }
        binding.pin5.setOnClickListener { onPinDigitPressed(5) }
        binding.pin6.setOnClickListener { onPinDigitPressed(6) }
        binding.pin7.setOnClickListener { onPinDigitPressed(7) }
        binding.pin8.setOnClickListener { onPinDigitPressed(8) }
        binding.pin9.setOnClickListener { onPinDigitPressed(9) }

        return binding.root
    }

    // метод для обработки нажатий на кнопки с цифрами
    private fun onPinDigitPressed(digit: Int) {
        if (pinCode.length < pinCircles.size) {
            pinCode += digit.toString()
            updatePinCircles()
        }
    }

    // метод для обновления списка кружочков
    private fun updatePinCircles() {
        pinCircles.forEachIndexed { index, view ->
            if (index < pinCode.length) {
                view.setBackgroundResource(R.drawable.circle_blue)
            } else {
                view.setBackgroundResource(R.drawable.circle_circle)
            }
        }
        // если пин код подходит, то переход
        if (pinCode.length == pinCircles.size) {
            navigateToCreateUserFragment()
        }
    }

    // метод для перехода к следующему фрагменту
    private fun navigateToCreateUserFragment() {
        findNavController().navigate(R.id.action_passwordFragment_to_createUser)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


