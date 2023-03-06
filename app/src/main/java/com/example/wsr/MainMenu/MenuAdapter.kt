package com.example.wsr.MainMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.R

class MenuAdapter(private val menuLsit: List<ItemMenu>):RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){

    class MenuViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.test)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_firs_recycler_view,parent,false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuLsit.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = menuLsit[position]
        holder.name.text = item.name
    }

}
