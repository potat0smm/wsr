package com.example.wsr.User

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.wsr.Api.RetrofitClient
//import com.example.wsr.MainMenu.MenuFragment
import com.example.wsr.R
import com.example.wsr.databinding.FragmentUserBinding
import java.io.FileNotFoundException
import java.io.InputStream


@Suppress("DEPRECATION")
class UserFragment : Fragment() {

    private val REQUEST_IMAGE_CAPTURE = 1


    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val apiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(inflater,container,false)

        binding.addUser.setOnClickListener {

        }
        val male = resources.getStringArray(R.array.MaleFemale)
        val arrayAdapter = ArrayAdapter(requireContext(), com.google.android.material.R.id.dropdown_menu,male)
        binding.autoCompleteUser.setAdapter(arrayAdapter)

        val items = listOf("Мужчина", "Девушка")
        val autoCompleteUser: AutoCompleteTextView = binding.autoCompleteUser
        val adapter = ArrayAdapter(requireContext(),R.layout.item_for_auto_complete,items)
        autoCompleteUser.setAdapter(adapter)

        autoCompleteUser.onItemClickListener = AdapterView.OnItemClickListener{adapterView, view, i, l->
            adapterView.getItemAtPosition(i)
        }

        binding.imageUser.setOnClickListener {
            openGallery()
        }


        return binding.root
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            try {
                val inputStream: InputStream? = imageUri?.let { context?.contentResolver?.openInputStream(it) }
                val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
               // binding.imageUser.setImageBitmap(bitmap)

                Glide.with(requireActivity())
                    .load(bitmap)
                    .apply(RequestOptions().transform(RoundedCorners(500)))
                    .override(150,150)
                    .into(binding.imageUser)


            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}