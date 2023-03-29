package com.example.wsr.MainMenu

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.Catalog
import com.example.wsr.Api.CatalogItem
import com.example.wsr.ManyFragment.MainFragment
import com.example.wsr.ManyFragment.MainFragmentDirections
import com.example.wsr.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView

class MenuAdapterThird(var analysisList: List<CatalogItem>, ) : RecyclerView.Adapter<MenuAdapterThird.AnalysisViewHolder>() {

    inner class AnalysisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val price: TextView = itemView.findViewById(R.id.price)
        val timeResult: TextView = itemView.findViewById(R.id.timeResult)
        val add: MaterialButton = itemView.findViewById(R.id.add_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_third_recycler_view, parent, false)
        return AnalysisViewHolder(view)
    }

    override fun getItemCount(): Int {
        return analysisList.size
    }

    override fun onBindViewHolder(holder: AnalysisViewHolder, position: Int) {
        val item = analysisList[position]
        holder.name.text = item.name
        holder.price.text = item.price
        holder.timeResult.text = item.time_result

        holder.itemView.setOnClickListener {view->
            val action = MainFragmentDirections.actionMainFragmentToBottomSheet()
            view.findNavController().navigate(action)
        }



        if (item.isAddedToCart) {
            holder.add.text = "Убрать"
            holder.add.setBackgroundResource(R.drawable.bottom_less_menu)
            holder.add.setTextColor(Color.parseColor("#007AFF"))
        } else {
            holder.add.text = "Добавить"
            holder.add.setBackgroundResource(R.drawable.background_button_one)
            holder.add.setTextColor(Color.parseColor("#FFFFFFFF"))
        }

        holder.add.setOnClickListener {

            item.isAddedToCart = !item.isAddedToCart // изменение состояния элемента списка

            if (item.isAddedToCart) {
                holder.add.text = "Убрать"
                holder.add.setBackgroundResource(R.drawable.bottom_less_menu)
                holder.add.setTextColor(Color.parseColor("#007AFF"))
            } else {
                holder.add.text = "Добавить"
                holder.add.setBackgroundResource(R.drawable.background_button_one)
                holder.add.setTextColor(Color.parseColor("#FFFFFFFF"))
            }
        }
    }
}



