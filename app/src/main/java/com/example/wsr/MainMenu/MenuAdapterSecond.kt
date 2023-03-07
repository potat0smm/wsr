package com.example.wsr.MainMenu

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.R

class MenuAdapterSecond(private val menuSecondList: List<ItemMenuSecond>):RecyclerView.Adapter<MenuAdapterSecond.SecondMenuViewHolder>(){

    class SecondMenuViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val nameSecond: TextView = itemView.findViewById(R.id.name_second)
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
        holder.nameSecond.text = item.name
    }


}