package com.example.wsr.Finish

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.navigation.fragment.findNavController
import com.example.wsr.databinding.FragmentFinishBinding


class FinishFragment : Fragment() {

    private var _binding: com.example.wsr.databinding.FragmentFinishBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
     // Inflate the layout for this fragment
        _binding = FragmentFinishBinding.inflate(layoutInflater,container,false)

        val progressBar = binding.progressBar
        val text = binding.textFinish
        Handler().postDelayed({
            text.text = "Производим оплату..."
        },3000)
        Handler().postDelayed({
            val action = FinishFragmentDirections.actionFinishFragmentToCheckFragment()
            findNavController().navigate(action)
        },7000)

        return binding.root
    }

}