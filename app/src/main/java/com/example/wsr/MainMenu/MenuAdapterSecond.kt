package com.example.wsr.MainMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.CatalogItem
import com.example.wsr.Api.model.SharedViewModel
import com.example.wsr.R

class MenuAdapterSecond(
    private val sharedViewModel: SharedViewModel // Передача sharedViewModel в конструктор адаптера
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var menuSecondList: List<CatalogItem> = emptyList()

    companion object {
        private const val ITEM_TYPE_POPULAR = 0
        private const val ITEM_TYPE_HEALTHY = 1
        private const val ITEM_TYPE_COVID = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_POPULAR -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_second_recycler_view, parent, false)
                PopularViewHolder(view)
            }
            ITEM_TYPE_HEALTHY -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_second_recycler_view, parent, false)
                HealthyViewHolder(view)
            }
            ITEM_TYPE_COVID -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_second_recycler_view, parent, false)
                CovidViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = menuSecondList[position]

        when (holder) {
            is PopularViewHolder -> {

                holder.itemView.setOnClickListener {
                    sharedViewModel.updateSelectedItem(item)
                }
            }
            is HealthyViewHolder -> {
                // Заполнение данных для элемента ЗОЖ
                // ...
            }
            is CovidViewHolder -> {
                // Заполнение данных для элемента Covid
                // ...
            }
        }
    }

    override fun getItemCount(): Int {
        return menuSecondList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = menuSecondList[position]
        return when (item.category) {
            "популярный" -> ITEM_TYPE_POPULAR
            "ЗОЖ" -> ITEM_TYPE_HEALTHY
            "Covid" -> ITEM_TYPE_COVID
            else -> throw IllegalArgumentException("Invalid category: ${item.category}")
        }
    }

    // Вспомогательные классы ViewHolder для каждого типа элемента
    inner class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class HealthyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class CovidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
/*
class MenuAdapterSecond(var menuSecondList: List<CatalogItem>):RecyclerView.Adapter<MenuAdapterSecond.SecondMenuViewHolder>(){

    class SecondMenuViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val category: TextView = itemView.findViewById(R.id.category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_second_recycler_view,parent,false)
        return SecondMenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuSecondList.size
    }

    override fun onBindViewHolder(holder: SecondMenuViewHolder, position: Int) {
        val item = menuSecondList[position]
        holder.category.text = item.category

        if (item.button){
            holder.itemView.setBackgroundResource(R.drawable.background_button_second)
            holder.category.setTextColor(Color.parseColor("#7E7E9A"))
        }else{
            holder.itemView.setBackgroundResource(R.drawable.background_button_one)
            holder.category.setTextColor(Color.parseColor("#FFFFFFFF"))
        }

        holder.itemView.setOnClickListener {
            item.button = !item.button

            if (item.button){
                holder.itemView.setBackgroundResource(R.drawable.background_button_second)
                holder.category.setTextColor(Color.parseColor("#7E7E9A"))
            }
            else{
                holder.itemView.setBackgroundResource(R.drawable.background_button_one)
                holder.category.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
        }
    }
}
 */