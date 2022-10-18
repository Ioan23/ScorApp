package com.xaivision.scorapp.main.model.repository

import com.xaivision.scorapp.main.model.models.Scor
import javax.inject.Inject

class HomeRepository @Inject constructor() {

    fun fetchScorList(): List<Scor> = listOf(
        Scor("♰", "1Slujba Sf Ioan Iacob","10.10.2020"),
//        Scor("♰", "2Slujba Sf Ioan Iacob","10.10.2020"),
//        Scor("♰", "3Slujba Sf Ioan Iacob","10.10.2020"),
//        Scor("♰", "4Slujba Sf Ioan Iacob","10.10.2020"),
//        Scor("♰", "5Slujba Sf Ioan Iacob","10.10.2020"),
//        Scor("♰", "6Slujba Sf Ioan Iacob","10.10.2020")
    )
}
