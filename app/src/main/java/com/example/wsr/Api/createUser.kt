package com.example.wsr.Api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class createUser(
    @SerializedName("errors")
    val errors:String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("bith")
    val bith: String,
    @SerializedName("firstname")
    val firstname: String,
   // @SerializedName("image")
 //   val image: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("middlename")
    val middlename: String,
    @SerializedName("pol")
    val pol: String
):Parcelable