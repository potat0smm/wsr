package com.example.wsr.Verification

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wsr.Api.RetrofitClient
import com.example.wsr.Api.model.SignInViewModel
import com.example.wsr.Api.signIn
import com.example.wsr.R
import com.example.wsr.databinding.FragmentCodeBinding
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CodeFragment : Fragment(R.layout.fragment_code) {

    private lateinit var timerTextView: TextView
    private lateinit var editTextList: List<EditText>
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis = 60000L
    private var binding: FragmentCodeBinding? = null
    private val apiService = RetrofitClient.apiService
    private lateinit var signInViewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCodeBinding.inflate(inflater, container, false)
        binding?.backToEmailFragment?.setOnClickListener {
            findNavController().navigate(R.id.action_codeFragment_to_emailFragment)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        showKeyboardForFirstEditText()
        signInViewModel = ViewModelProvider(requireActivity()).get(SignInViewModel::class.java)
    }

    private fun initViews() {
        timerTextView = binding!!.time
        editTextList = listOf(binding!!.OTP1, binding!!.OTP2, binding!!.OTP3, binding!!.OTP4)
    }

    private fun initListeners() {
        editTextList.forEachIndexed { index, editText ->
            editText.addTextChangedListener(
                CodeTextWatcher(
                    editTextList.getOrNull(index + 1),
                    editTextList.getOrNull(index - 1),
                    editText
                )
            )
        }
    }

    private fun showKeyboardForFirstEditText() {
        val firstEditText = editTextList.firstOrNull()
        firstEditText?.requestFocus()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(firstEditText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun navigateToNextFragment() {
        val email = arguments?.getString("email") ?: ""
        val code = editTextList.joinToString("") { it.text.toString() }
        GlobalScope.launch {
            apiService.signIn(email, code).enqueue(object : Callback<signIn> {
                override fun onResponse(call: Call<signIn>, response: Response<signIn>) {
                    if (response.isSuccessful) {
                        val token = response.body()?.token ?: ""
                        signInViewModel.saveToken(token)
                        //Проверить токен
                        //Toast.makeText(requireContext(), token,Toast.LENGTH_SHORT).show()
                        val action = CodeFragmentDirections.actionCodeFragmentToPasswordFragment()
                        findNavController().navigate(action)
                    } else {
                        // Если пароль неверный, показываем Toast с ошибкой
                        Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
                        // Запускаем таймер на 30 секунд
                        countDownTimer = object : CountDownTimer(60000, 1000) {
                            override fun onTick(millisUntilFinished: Long) {
                                // Обновляем текст таймера каждую секунду
                                timerTextView.text = "Отпраивть код повторно можно\n" + "будет через ${millisUntilFinished / 1000} секунды"
                            }
                            override fun onFinish() {
                                // Сбрасываем текст таймера и разблокируем все EditText
                                timerTextView.text = ""
                                editTextList.forEach { it.isEnabled = true }
                            }
                        }.start()
                        // Блокируем все EditText на время таймера
                        editTextList.forEach { it.isEnabled = false }
                    }
                }
                override fun onFailure(call: Call<signIn>, t: Throwable) {

                }
            })
        }
    }

    inner class CodeTextWatcher(
        private val nextEditText: EditText?,
        private val prevEditText: EditText?,
        private val currentEditText: EditText
    ) : TextWatcher {
        private var timerStarted = false
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.length == 1) {
                nextEditText?.let {
                    it.requestFocus()
                    it.setSelection(it.text.length)
                } ?: run {
                    navigateToNextFragment()
                }
            } else if ((s?.length ?: 0) > 1) {
                prevEditText?.let {
                    it.requestFocus()
                    it.setSelection(it.text.length)
                }
            }
        }
        override fun afterTextChanged(s: Editable?) {
            if (s?.length == 4 && !timerStarted) {
                if (s?.length == 4 && !timerStarted && editTextList.all { it.text.isNotEmpty() && it.text.isDigitsOnly() }) {
                    navigateToNextFragment()
                } else {
                    Toast.makeText(requireContext(), "Incorrect Verification Code", Toast.LENGTH_SHORT).show()
                    // Запускаем таймер на 60 секунд
                    countDownTimer = object : CountDownTimer(60000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            timerStarted = true
                            timerTextView.text = "Try again in ${millisUntilFinished / 1000} seconds"
                        }

                        override fun onFinish() {
                            timerStarted = false
                            timerTextView.text = ""
                            editTextList.forEach { it.isEnabled = true }
                        }
                    }.start()
                    editTextList.forEach { it.isEnabled = false }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
   companion object {
       fun newInstance(email: String): CodeFragment {
           val fragment = CodeFragment()
           val args = Bundle()
           args.putString("email", email)
           fragment.arguments = args
           return fragment
       }
   }
}