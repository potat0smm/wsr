package com.example.wsr.ViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(list:ArrayList<Fragment>,fm: FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fm,lifecycle) {
    private val fragmentList = list


    companion object{
        const val SHOW_IMAGE_ON_TOP = 1
        const val SHOW_IMAGE_ON_BOTTOM = 2
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemViewType(position: Int): Int {
        return SHOW_IMAGE_ON_TOP.takeIf { position % 2 == 0 }?: SHOW_IMAGE_ON_BOTTOM
    }

}