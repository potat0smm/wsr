package com.example.wsr.ManyFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsr.MainMenu.MenuFragment
import com.example.wsr.R
import com.example.wsr.User.UserFragment
import com.example.wsr.databinding.FragmentMainBinding


@Suppress("UNUSED_EXPRESSION")
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        replaceFragment(MenuFragment())

        binding?.bottomNavigationView?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.analyzes -> replaceFragment(MenuFragment())
                R.id.profil -> replaceFragment(UserFragment())
                else -> {}
            }
            true
        }

        return binding?.root
    }

    private fun replaceFragment(fragment: Fragment?) {
        fragment?.let {
            val fragmentManager = childFragmentManager
            val fragmentTransition = fragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.frame_layout, it)
            fragmentTransition.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
