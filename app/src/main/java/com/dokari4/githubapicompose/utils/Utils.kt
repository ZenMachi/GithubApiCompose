package com.dokari4.githubapicompose.utils

import kotlin.math.log10
import kotlin.math.pow

fun formatNumber(number: Long): String {
    if (number < 1000) return number.toString()
    val exp = (log10(number.toDouble()) / 3).toInt()
    return String.format("%.1f%c", number / 10.0.pow((exp * 3).toDouble()), "kMBTPE"[exp - 1])
}