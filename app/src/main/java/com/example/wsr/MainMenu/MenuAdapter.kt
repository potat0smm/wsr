package com.example.wsr.MainMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.NewsItem
import com.example.wsr.R
import com.squareup.picasso.Picasso

class MenuAdapter(var menuList: List<NewsItem>):RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){

    class MenuViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val firstName: TextView = itemView.findViewById(R.id.first_name)
        val firstTitle: TextView = itemView.findViewById(R.id.title_first)
        val firstPrice: TextView = itemView.findViewById(R.id.price_first)
        val firstImg: ImageView = itemView.findViewById(R.id.img_first)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_first_recycler_view,parent,false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = menuList[position]
        holder.firstName.text = item.name
        holder.firstTitle.text = item.description
        holder.firstPrice.text = item.price
        Picasso.get().load(item.image).into(holder.firstImg)
    }

}
