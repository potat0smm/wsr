package com.example.wsr.Verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wsr.R
import com.google.android.material.button.MaterialButton


class EmailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_email, container, false)

        view.findViewById<MaterialButton>(R.id.materialButton).setOnClickListener {
            findNavController().navigate(R.id.action_emailFragment_to_codeFragment)
        }

        return view
    }

}