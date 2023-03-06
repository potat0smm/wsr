package com.example.wsr.MainMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide.init
import com.example.wsr.R
import com.example.wsr.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemlist: ArrayList<ItemMenu>
    private lateinit var menuAdapter: MenuAdapter

    private lateinit var secondItemList: ArrayList<ItemMenuSecond>
    private lateinit var secondMenuAdapter: MenuAdapterSecond

    private lateinit var thirdItemList: ArrayList<ItemMenuThird>
    private lateinit var thirdMenuAdapter: MenuAdapterThird

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMenuBinding.inflate(layoutInflater,container,false)

        init()
        initSecond()
        initThird()
        return binding.root
    }

    private fun initThird() {
        recyclerView = binding.thirdRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        thirdItemList = ArrayList()

        addDataListThird()

        thirdMenuAdapter = MenuAdapterThird(thirdItemList)
        recyclerView.adapter = thirdMenuAdapter
    }

    private fun addDataListThird() {
        thirdItemList.add(ItemMenuThird("test0"))
        thirdItemList.add(ItemMenuThird("test1"))
        thirdItemList.add(ItemMenuThird("test2"))
        thirdItemList.add(ItemMenuThird("test3"))
        thirdItemList.add(ItemMenuThird("test4"))
        thirdItemList.add(ItemMenuThird("test5"))
        thirdItemList.add(ItemMenuThird("test6"))
    }

    private fun initSecond() {
        recyclerView = binding.secondRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        secondItemList = ArrayList()

        addDataListSecond()

        secondMenuAdapter = MenuAdapterSecond(secondItemList)
        recyclerView.adapter = secondMenuAdapter

    }

    private fun addDataListSecond() {
        secondItemList.add(ItemMenuSecond("test0"))
        secondItemList.add(ItemMenuSecond("test1"))
        secondItemList.add(ItemMenuSecond("test2"))
        secondItemList.add(ItemMenuSecond("test3"))
        secondItemList.add(ItemMenuSecond("test4"))
        secondItemList.add(ItemMenuSecond("test5"))
        secondItemList.add(ItemMenuSecond("test6"))
    }

    private fun init() {
        recyclerView = binding.firstRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
        val snapHelper : SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        itemlist = ArrayList()

        addDataList()

        menuAdapter = MenuAdapter(itemlist)
        recyclerView.adapter = menuAdapter
    }



    private fun addDataList() {
        itemlist.add(ItemMenu("test0"))
        itemlist.add(ItemMenu("test1"))
    }

}