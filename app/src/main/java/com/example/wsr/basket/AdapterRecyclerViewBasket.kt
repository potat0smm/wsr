package com.example.wsr.basket

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.CatalogItem
import com.example.wsr.R
import com.example.wsr.databinding.ItemInBasketFragmentBinding

class AdapterRecyclerViewBasket(var analysisList: List<CatalogItem>): RecyclerView.Adapter<AdapterRecyclerViewBasket.AnalysisListViewHolder>(){
    inner class AnalysisListViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.title_basket)
        val price: TextView = itemView.findViewById(R.id.price_basket)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_basket_fragment,parent,false)
        return AnalysisListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return analysisList.size
    }

    override fun onBindViewHolder(holder: AnalysisListViewHolder, position: Int) {
        val item = analysisList[position]
        holder.name.text = item.name
        holder.price.text = item.price
    }


}