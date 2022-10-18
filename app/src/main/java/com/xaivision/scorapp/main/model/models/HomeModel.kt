package com.xaivision.scorapp.main.model.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeModel(
    val scorsList: List<Scor> = listOf(),
    val selectedScor: Scor? = null
): Parcelable

@Parcelize
data class Scor(
    val title: String = "",
    val description: String ="",
    val date: String="",
) : Parcelable
