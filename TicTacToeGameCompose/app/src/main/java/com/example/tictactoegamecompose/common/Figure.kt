package com.example.tictactoegamecompose.common

import android.graphics.drawable.GradientDrawable.RECTANGLE
import com.example.tictactoegamecompose.R

enum class Figure(val str: String, val imageID: Int) {
    CROSS(str = "x", imageID = R.drawable.cross),
    CIRCLE(str = "o",imageID = R.drawable.circle),
    TRIANGLE(str = "*",imageID = R.drawable.triangle),
    RECTANGLE(str = "+",imageID = R.drawable.rectangle),
}