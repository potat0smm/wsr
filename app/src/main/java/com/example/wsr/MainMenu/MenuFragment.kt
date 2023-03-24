package com.example.wsr.MainMenu

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide.init
import com.example.wsr.R
import com.example.wsr.databinding.FragmentMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayout.LabelVisibility
import com.example.wsr.R.id.menuFragment2 as menuFragment2


@Suppress("DEPRECATION")
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
        _binding = FragmentMenuBinding.inflate(layoutInflater,container,false)

        init()
        initSecond()
        initThird()
        return binding.root
    }
    @SuppressLint("CommitTransaction")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = binding.bottomNav


        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.profil -> {
                    findNavController().navigate(R.id.userFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.analyzes ->{
                    findNavController().navigate(R.id.createUser)
                    return@setOnItemSelectedListener true
                }
            }
            true
        }
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
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽"))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽"))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽"))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽"))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽"))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽"))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽"))

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
        secondItemList.add(ItemMenuSecond("Популярный"))
        secondItemList.add(ItemMenuSecond("Covid"))
        secondItemList.add(ItemMenuSecond("ТЕСТ"))
        secondItemList.add(ItemMenuSecond("Тест"))
        secondItemList.add(ItemMenuSecond("апвапвапвапвпа"))
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
        itemlist.add(ItemMenu("Чек-ап для\nмужчин","9 исследований","8000 ₽"))
        itemlist.add(ItemMenu("Подготовка к\nвакцинации","Комплексное обследование\nперед вакцинацией","4000 ₽"))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}