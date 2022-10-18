package com.xaivision.scorapp.ui.components

import com.xaivision.scorapp.main.model.models.Scor

const val COMMA: String = ", "
fun Scor.asFormattedScor(): String {
    return this.title.plus(COMMA).plus(this.description).let {
        if (this.date.isNotEmpty()) {
            it.plus(COMMA).plus(this.date)
        } else {
            it
        }
    }
}



