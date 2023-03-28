package com.example.wsr.MainMenu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.wsr.Api.model.MainViewModel
import com.example.wsr.databinding.FragmentMenuBinding
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module


class MenuFragment : Fragment() {

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var itemlist: ArrayList<ItemMenu>


    private lateinit var secondItemList: ArrayList<ItemMenuSecond>
    private lateinit var secondMenuAdapter: MenuAdapterSecond

    private lateinit var recyclerView: RecyclerView
    private lateinit var thirdMenuAdapter: MenuAdapterThird
    private val viewModel: MainViewModel by viewModel()

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(layoutInflater,container,false)


            recyclerView = binding.thirdRecyclerView
            thirdMenuAdapter = MenuAdapterThird(emptyList(),{item -> viewModel.addItemToCart(item)})
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = thirdMenuAdapter

            viewModel.catalogList.observe(viewLifecycleOwner, Observer { catalogList ->
                val catalogItemList = catalogList.flatMap { it.items }
                thirdMenuAdapter = MenuAdapterThird(catalogItemList) { item -> viewModel.addItemToCart(item) }
                recyclerView.adapter = thirdMenuAdapter
            })

        init()
        initSecond()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    /*private fun initThird() {
        recyclerView = binding.thirdRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        thirdItemList = ArrayList()

        addDataListThird()

        thirdMenuAdapter = MenuAdapterThird(menuThirdList)
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
    }*/
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