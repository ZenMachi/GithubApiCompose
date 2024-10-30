package com.dokari4.githubapicompose.utils

import android.os.Build
import java.util.Locale
import kotlin.math.log10
import kotlin.math.pow

const val POWER_OF_TEN_FOR_SUFFIX = 3

fun formatNumber(number: Long): String {
    if (number == 0L) return "0"
    if (number < 0) return "-" + formatNumber(-number)
    if (number < 1000) return number.toString()

    val suffixIndex = (log10(number.toDouble()) / POWER_OF_TEN_FOR_SUFFIX).toInt()
    val suffix = when (suffixIndex) {
        1 -> "k"
        2 -> "M"
        3 -> "B"
        4 -> "T"
        5 -> "P"
        6 -> "E"
        else -> ""
    }
    return String.format(Locale.getDefault(),"%.1f%s", number / 10.0.pow((suffixIndex * 3).toDouble()), suffix)
}

val isDynamicColorSupported: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S