package com.example.wsr.MainMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.CatalogItem
import com.example.wsr.Api.model.SharedViewModel
import com.example.wsr.R
import com.example.wsr.databinding.ItemSecondRecyclerViewBinding

class MenuAdapterSecond(private val onItemClick: (String) -> Unit):RecyclerView.Adapter<MenuAdapterSecond.SecondMenuViewHolder>(){

    private val filterItems = mutableListOf("Популярный", "COVID", "Онкогенетические", "ЗОЖ")
    private var selectedFilter: String? = null

    inner class SecondMenuViewHolder(private val binding: ItemSecondRecyclerViewBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(filterItem: String) {
            binding.category.text = filterItem
            binding.root.setOnClickListener {
                selectedFilter = filterItem
                onItemClick(filterItem)
                notifyDataSetChanged()
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondMenuViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemSecondRecyclerViewBinding.inflate(inflate,parent,false)
        return SecondMenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filterItems.size
    }

    override fun onBindViewHolder(holder: SecondMenuViewHolder, position: Int) {
        holder.bind(filterItems[position])
    }
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