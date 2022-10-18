package com.xaivision.scorapp.personaldata.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat

@Parcelize
data class ScorDataState(
    val title: String = "",
    val description: String = "",
    val date: String = "",
    ) : Parcelable


