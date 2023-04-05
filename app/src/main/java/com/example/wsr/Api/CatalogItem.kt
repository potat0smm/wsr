package com.example.wsr.Api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class CatalogItem(
    val id: Int,
    val name: String,
    val description: String,
    val preparation: String,
    val price: String,
    val category: String,
    val time_result: String,
    val bio: String,
    var isAddedToCart: Boolean,
    var button: Boolean
    )
