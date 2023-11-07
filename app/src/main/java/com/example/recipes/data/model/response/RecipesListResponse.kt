package com.example.recipes.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipesListResponse(
    @SerializedName("results")
    val results: List<RecipesDataResponse>
): Parcelable