package com.example.wsr.ViewPager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.wsr.OnBoarding.FirstScreen
import com.example.wsr.OnBoarding.SecondScreen
import com.example.wsr.OnBoarding.ThirdScreen
import com.example.wsr.R
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator


class ViewPagerFragment : Fragment() {

    private var _binding:ViewPagerFragment? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    @SuppressLint("CutPasteId", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,
        lifecycle)

        val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.worm_dot)
       val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = adapter



        //val indicator = view.findViewById<WormDotsIndicator>(R.id.worm_dot)
        //indicator.setViewPager2(view.findViewById(R.id.viewPager))
        return view
    }


}
