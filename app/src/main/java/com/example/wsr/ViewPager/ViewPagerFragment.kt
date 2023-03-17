package com.example.wsr.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.wsr.OnBoarding.FirstScreen
import com.example.wsr.OnBoarding.SecondScreen
import com.example.wsr.OnBoarding.ThirdScreen
import com.example.wsr.R
import com.example.wsr.databinding.FragmentViewPagerBinding


class ViewPagerFragment : Fragment() {

    private var _binding:FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager2: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var dots: Array<ImageView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(layoutInflater,container,false)

        /* val fragmentList = arrayListOf<Fragment>(
             FirstScreen(),
             SecondScreen(),
             ThirdScreen()
         )*/

        // val adapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,
        //lifecycle)

        //val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        //viewPager.adapter = adapter

        viewPager2 = binding.viewPager
        dotsLayout = binding.layoutDots

        setUpDots()
        setCurrentDot(0)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentDot(position)
            }
        })

        return binding.root
    }

    private fun setUpDots() {
        val onBoardingFragments = listOf(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )
        val adapter = ViewPagerAdapter(requireActivity(), onBoardingFragments)
        viewPager2.adapter = adapter

        dots = Array(onBoardingFragments.size) { ImageView(requireContext()) }
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 0, 8, 0)
        for (i in dots.indices) {
            dots[i] = ImageView(requireContext())
            dots[i].setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext().applicationContext,
                    R.drawable.ellpse_two
                )
            )
            dotsLayout.addView(dots[i], params)
        }
    }

    private fun setCurrentDot(position: Int) {
        for (i in dots.indices) {
            dots[i].setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext().applicationContext,
                    R.drawable.ellpse_two
                )
            )
        }
        dots[position].setImageDrawable(
            ContextCompat.getDrawable(
                requireContext().applicationContext,
                R.drawable.ellipse
            )
        )
    }


}






