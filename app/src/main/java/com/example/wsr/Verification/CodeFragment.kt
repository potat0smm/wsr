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


@Suppress("DEPRECATION", "UNREACHABLE_CODE")
class CodeFragment : Fragment(R.layout.fragment_code) {

    companion object{
        private const val TEST_VERIFY_CODE = "1111"
    }

    private val frameLayoutRoot: FrameLayout by lazy {
        frameLayoutRoot
    }

    private val editTextOne: EditText by lazy{
        binding.OTP1
    }
    private val editTextTwo: EditText by lazy {
        binding.OTP2
    }
    private val editTextThree: EditText by lazy {
        binding.OTP4
    }
    private val editTextFour: EditText by lazy {
        binding.OTP3
    }

    private var _binding: FragmentCodeBinding? = null
    private val binding get() = _binding!!



    private fun setListener() {
     frameLayoutRoot.setOnClickListener {
         val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
         inputManager.hideSoftInputFromWindow(frameLayoutRoot.windowToken,0)
     }
        setTextChangeListener(fromEditText = editTextOne, targerEditText = editTextTwo)
        setTextChangeListener(fromEditText = editTextTwo, targerEditText = editTextThree)
        setTextChangeListener(fromEditText = editTextThree, targerEditText = editTextFour)
        setTextChangeListener(fromEditText = editTextFour, done = {
            verifyOTPCode()
        })
        setKeyListener(fromEditText = editTextTwo, backToEditText = editTextOne)
        setKeyListener(fromEditText = editTextThree, backToEditText = editTextTwo)
        setKeyListener(fromEditText = editTextFour, backToEditText = editTextThree)
    }
        private fun initFocus(){
            editTextOne.isEnabled = true

            editTextOne.postDelayed({
                editTextOne.requestFocus()
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            },500)
        }

    private fun getSystemService(inputMethodService: String): Any {
        null!!
    }
    private fun reset(){
        editTextOne.isEnabled = false
        editTextTwo.isEnabled = false
        editTextThree.isEnabled = false
        editTextFour.isEnabled = false

        editTextOne.setText("")
        editTextTwo.setText("")
        editTextThree.setText("")
        editTextFour.setText("")

        initFocus()
    }

    private fun setTextChangeListener(
        fromEditText: EditText,
        targerEditText: EditText? = null,
        done: (() -> Unit)? = null
    ){
        fromEditText.addTextChangedListener {
            it?.let {string ->
                if(string.isNotEmpty()){
                    targerEditText?.let {editText ->
                        editText.isEnabled =true
                        editText.requestFocus()
                    }?: run{
                        done?.let { done ->
                            done()
                        }
                    }
                    fromEditText.clearFocus()
                    fromEditText.isEnabled = false
                }

            }
        }
    }
    private fun setKeyListener(fromEditText: EditText,backToEditText:EditText){
        fromEditText.setOnKeyListener{ _, _, event ->
            if (null != event && KeyEvent.KEYCODE_DEL == event.keyCode){
                backToEditText.isEnabled = true
                backToEditText.requestFocus()
                backToEditText.setText("")

                fromEditText.clearFocus()
                fromEditText.isEnabled = false
            }
            false
        }
    }
    private fun verifyOTPCode(){
        val otpCode = "${editTextOne.text.toString().trim()}"+
                "${editTextTwo.text.toString().trim()}"+
                "${editTextThree.text.toString().trim()}"+
                "${editTextFour.text.toString().trim()}"
        if (4 != otpCode.length){
            return
        }
        if (otpCode == TEST_VERIFY_CODE){
            findNavController().navigate(R.id.action_codeFragment_to_passwordFragment)

            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(frameLayoutRoot.windowToken,0)

            return
        }
        reset()

    }


    //binding.OTP1.postDelayed({
          //  binding.OTP1.requestFocus()
            //val inputMethodManager = getSystemService()
        //},500)




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCodeBinding.inflate(layoutInflater, container, false)
        binding.backToEmailFragment.setOnClickListener{
            findNavController().navigate(R.id.action_codeFragment_to_emailFragment)
        }
        setListener()

        initFocus()

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

