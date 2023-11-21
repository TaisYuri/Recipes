package com.example.recipes.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SupermarketProductListResponse(
    @SerializedName("results")
    val results: List<SupermarketProductResponse>,
): Parcelable

@Parcelize
data class SupermarketProductResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String
):Parcelable