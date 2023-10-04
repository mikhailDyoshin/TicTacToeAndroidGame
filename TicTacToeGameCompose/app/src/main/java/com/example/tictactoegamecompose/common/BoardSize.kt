package com.example.tictactoegamecompose.common

enum class BoardSize(val str: String, val size: Int) {
    SMALL(str="3x3", size=3),
    MIDDLE(str="5x5", size=5),
    BIG(str="7x7", size=7),
}