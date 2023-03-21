package com.example.wsr.Verification

import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.example.wsr.databinding.FragmentEmailBinding


class EmailFragment : Fragment() {



    private var _binding: FragmentEmailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentEmailBinding.inflate(layoutInflater,container,false)

        signUp()


       // view.findViewById<MaterialButton>(R.id.materialButton).setOnClickListener {
         //   findNavController().navigate(R.id.action_emailFragment_to_codeFragment)
       // }

        return binding.root
    }
    private fun validateForm(): Boolean {
        val icon = AppCompatResources.getDrawable(requireContext(),R.drawable.baseline_warning_24)

        icon?.setBounds(0,0,icon.intrinsicWidth,icon.intrinsicHeight)
        when{
            TextUtils.isEmpty(binding.editText.text.toString().trim())->{
                binding.editText.setError("Please Enter Email",icon)
            }
            binding.editText.text.toString().isNotEmpty()->{
                if (binding.editText.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    return true
                }else{
                    binding.editText.setError("Please Valid",icon)
                }
            }
        }
        return false
    }
    private fun signUp(){
        binding.materialButton.isEnabled = false
         binding.editText.addTextChangedListener {
            val email = binding.editText.text.toString()
            var status = false
            if (email.isNotEmpty())
                if(validateForm())
                    status = true
            binding.materialButton.isEnabled = status
            binding.materialButton.setOnClickListener{
                findNavController().navigate(R.id.action_emailFragment_to_codeFragment)
                //Toast.makeText(context,"Register successful",Toast.LENGTH_SHORT).show()
            }
        }



    }
}
