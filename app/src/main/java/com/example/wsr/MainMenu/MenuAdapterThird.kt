package com.example.wsr.MainMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.R

class MenuAdapterThird(private val menuThirdList: List<ItemMenuThird>):RecyclerView.Adapter<MenuAdapterThird.ThirdMenuViewHolder>() {

    class ThirdMenuViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.test3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_third_recycler_view,parent,false)
        return ThirdMenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuThirdList.size
    }

    override fun onBindViewHolder(holder: ThirdMenuViewHolder, position: Int) {
        val item = menuThirdList[position]
        holder.name.text = item.name
    }


}