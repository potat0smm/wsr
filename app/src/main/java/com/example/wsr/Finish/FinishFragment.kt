package com.example.wsr.Finish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsr.R
import com.example.wsr.databinding.FragmentFinishBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


class FinishFragment : Fragment() {

    private var _binding: FragmentFinishBinding?=null
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



        return binding.root
    }

}