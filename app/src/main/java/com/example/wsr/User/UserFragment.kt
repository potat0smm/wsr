package com.example.wsr.User

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
    private lateinit var pLauncher: ActivityResultLauncher<String>

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
            if(ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                checkCamera()
            }else{
                requestCameraPermission()
            }
        }
        return binding.root
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешение на использование камеры было предоставлено, открываем камеру
                checkCamera()
            } else {
                // Разрешение на использование камеры было отклонено, показываем сообщение
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun requestCameraPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            // Если пользователь уже отклонил разрешение, но еще не выбрал "Не спрашивать снова",
            // показываем ему объяснение необходимости разрешения
            Toast.makeText(requireContext(), "Need your permission to access the camera", Toast.LENGTH_SHORT).show()
        } else {
            // Запрашиваем разрешение
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), REQUEST_IMAGE_CAPTURE)
        }
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
                    .into(binding.imageUser)


            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }
    private fun checkCamera(){
        when{
            ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED ->{
                openGallery()
                Toast.makeText(requireContext(),"Camera run",Toast.LENGTH_SHORT).show()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)->{
                Toast.makeText(requireContext(),"Need yout permisson",Toast.LENGTH_SHORT).show()
            }
            else->{

            }
        }
    }
}