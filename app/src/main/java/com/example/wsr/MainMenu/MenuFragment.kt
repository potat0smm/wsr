package com.example.wsr.MainMenu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.wsr.databinding.FragmentMenuBinding

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

        val hiddenButton = binding.buttonGoBasketBasket
        hiddenButton.visibility = View.GONE


        init()
        initSecond()
        initThird()
        return binding.root
    }
    @SuppressLint("CommitTransaction")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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



        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽",false))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽",false))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽",false))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽",false))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽",false))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽",false))
        thirdItemList.add(ItemMenuThird("ПЦР-тест на определение РНК\nкоронавируса стандартный","2 дня","1800 ₽",false))
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
        secondItemList.add(ItemMenuSecond("Комплексные"))
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