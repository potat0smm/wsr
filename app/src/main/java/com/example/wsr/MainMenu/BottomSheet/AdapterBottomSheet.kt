package com.example.wsr.MainMenu.BottomSheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.wsr.Api.CatalogItem
import com.example.wsr.R
import com.example.wsr.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text

class AdapterBottomSheet(var list: List<CatalogItem>):RecyclerView.Adapter<AdapterBottomSheet.ListViewHolder>() {

    inner class ListViewHolder(private val binding: FragmentBottomSheetBinding ):RecyclerView.ViewHolder(binding.root) {
        val name:TextView = itemView.findViewById(R.id.name_bottom_sheet)
        val time:TextView = itemView.findViewById(R.id.time_bottomsheet)
        val description:TextView = itemView.findViewById(R.id.description)
        val bio: TextView = itemView.findViewById(R.id.bio)
        val preparation:TextView = itemView.findViewById(R.id.preparation)
        val btn:MaterialButton = itemView.findViewById(R.id.save_in_basket)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = FragmentBottomSheetBinding.inflate(inflate,parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.name
        holder.bio.text = item.bio
        holder.description.text = item.description
        holder.preparation.text = item.preparation
        holder.time.text = item.time_result
        holder.btn.setOnClickListener {
            //
        }
    }

}