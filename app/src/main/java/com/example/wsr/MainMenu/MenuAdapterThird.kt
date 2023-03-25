package com.example.wsr.MainMenu

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.ManyFragment.MainFragment
import com.example.wsr.ManyFragment.MainFragmentDirections
import com.example.wsr.R
import com.google.android.material.button.MaterialButton

class MenuAdapterThird(private val menuThirdList: List<ItemMenuThird>):RecyclerView.Adapter<MenuAdapterThird.ThirdMenuViewHolder>() {

    class ThirdMenuViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){


        val name: TextView = itemView.findViewById(R.id.name)
        val day: TextView = itemView.findViewById(R.id.day)
        val price: TextView = itemView.findViewById(R.id.price)

    // val image: ImageView = itemView.findViewById(R.id.add_menu)

       val add:MaterialButton = itemView.findViewById(R.id.add_menu)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapterThird.ThirdMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_third_recycler_view,parent,false)
        return MenuAdapterThird.ThirdMenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuThirdList.size
    }

    override fun onBindViewHolder(holder: ThirdMenuViewHolder, position: Int) {
        val item = menuThirdList[position]
        holder.name.text = item.name
        holder.day.text = item.day
        holder.price.text = item.price
        holder.itemView.setOnClickListener{view->
            val ai = MainFragmentDirections.actionMainFragmentToBottomSheet()
            view.findNavController().navigate(ai)
        }
        holder.add.setOnClickListener {view->
            val ik = MainFragmentDirections.actionMainFragmentToBottomSheet()
             view.findNavController().navigate(ik)
       }

    }

}