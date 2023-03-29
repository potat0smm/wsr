package com.example.wsr.MainMenu

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.CatalogItem
import com.example.wsr.R
import com.google.android.material.button.MaterialButton

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