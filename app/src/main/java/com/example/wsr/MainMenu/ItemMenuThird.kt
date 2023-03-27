package com.example.wsr.MainMenu

import android.os.Parcelable
import android.text.BoringLayout
import kotlinx.parcelize.Parcelize


data class ItemMenuThird(
    val name:String,
    val day: String,
    val price: String,
    var isButtonClicked: Boolean
    //val add:String
)

