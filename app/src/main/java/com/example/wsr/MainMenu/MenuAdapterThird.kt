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
import com.example.wsr.ManyFragment.MainFragment
import com.example.wsr.ManyFragment.MainFragmentDirections
import com.example.wsr.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView

class MenuAdapterThird(private val menuThirdList: List<ItemMenuThird>): RecyclerView.Adapter<MenuAdapterThird.ThirdMenuViewHolder>() {

    class ThirdMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val day: TextView = itemView.findViewById(R.id.day)
        val price: TextView = itemView.findViewById(R.id.price)
        val add: MaterialButton = itemView.findViewById(R.id.add_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdMenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_third_recycler_view, parent, false)
        return ThirdMenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuThirdList.size
    }

    override fun onBindViewHolder(holder: ThirdMenuViewHolder, position: Int) {
        val item = menuThirdList[position]
        holder.name.text = item.name
        holder.day.text = item.day
        holder.price.text = item.price

        if (item.isButtonClicked) { // если кнопка была нажата
            holder.add.text = "Убрать"
            holder.add.setBackgroundResource(R.drawable.bottom_less_menu)
            holder.add.setTextColor(Color.parseColor("#007AFF"))
        } else { // если кнопка не была нажата
            holder.add.text = "Добавить"
            holder.add.setBackgroundResource(R.drawable.background_button_one)
            holder.add.setTextColor(Color.parseColor("#FFFFFFFF"))
        }

        holder.itemView.setOnClickListener { view ->
            val ai = MainFragmentDirections.actionMainFragmentToBottomSheet()
            view.findNavController().navigate(ai)
        }

        holder.add.setOnClickListener { view ->
            item.isButtonClicked = !item.isButtonClicked //инвертируем состояние кнопки
            if (item.isButtonClicked) { // если кнопка была нажата
                holder.add.text = "Убрать"
                holder.add.setBackgroundResource(R.drawable.bottom_less_menu)

                /*
                //BottomSheetDialog
                val dialog = BottomSheetDialog(holder.itemView.context,R.style.MyDialogStyle)
                val view = LayoutInflater.from(holder.itemView.context).inflate(R.layout.fragment_bottom_sheet_go_basket,null)
                val button = view.findViewById<MaterialButton>(R.id.by_button)


                button.setOnClickListener {
                    dialog.dismiss()
                }
                //отключаем затемнение
                dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

                dialog.setContentView(view)
                dialog.show()
                 */

                // здесь можно вызвать метод, который открывает bottom sheet
            } else { // если кнопка не была нажата
                holder.add.text = "Добавить"
                holder.add.setBackgroundResource(R.drawable.background_button_one)
            }
            notifyItemChanged(position)
        }
    }
}
