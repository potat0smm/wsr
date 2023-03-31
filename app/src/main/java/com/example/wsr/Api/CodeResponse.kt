package com.example.wsr.Api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class signIn(
    @SerializedName("code")
    val code: String,
    @SerializedName("error")
    val error: String,
    @SerializedName("token")
    val token:String
):Parcelable
@Parcelize
 data class sendCode(
    @SerializedName("email")
    val email: String,
    @SerializedName("message")
    val message: String
):Parcelable
