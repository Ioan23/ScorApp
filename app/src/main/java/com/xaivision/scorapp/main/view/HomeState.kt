package com.xaivision.scorapp.main.view

import android.os.Parcelable
import com.xaivision.scorapp.main.model.models.HomeModel
import com.xaivision.scorapp.main.model.models.Scor
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeState(
    val homeModel: HomeModel =HomeModel(),
    val currentScor: Scor
    ): Parcelable

