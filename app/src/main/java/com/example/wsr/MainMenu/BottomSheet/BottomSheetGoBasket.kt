package com.example.wsr.MainMenu.BottomSheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.wsr.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetGoBasket : BottomSheetDialogFragment() {


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val dialog = BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)

            // Настройка внешнего вида вашего всплывающего окна
            dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            // Использование нового стиля
            setStyle(STYLE_NO_FRAME, R.style.MyDialogStyle)
            return inflater.inflate(R.layout.fragment_bottom_sheet_go_basket, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            // Обработчик нажатия кнопки "В корзину"
            view.findViewById<Button>(R.id.by_button).setOnClickListener {
                //обработать нажатие кнопки
                dismiss()
            }
        }

    /*
    val dialog = BottomSheetDialog(requireContext(), R.style.MyDialogStyle)
val view = LayoutInflater.from(requireContext()).inflate(R.layout.navigation_layout, null)

val navigationView = view.findViewById<NavigationView>(R.id.navigation_view)

// настраиваем NavigationView
navigationView.setNavigationItemSelectedListener { menuItem ->
    // обработка выбора пунктов меню
    dialog.dismiss()
    true
}

dialog.setContentView(view)
dialog.show()

     */

}