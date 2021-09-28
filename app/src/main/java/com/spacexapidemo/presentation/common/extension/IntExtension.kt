package com.spacexapidemo.presentation.common.extension

import java.text.SimpleDateFormat
import java.util.*

fun Int.toReadableDate(): String {
    val df = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.UK)

    return df.format(Date(this * 1_000L))
}

fun Int.toYear(): Int {
    val df = SimpleDateFormat("yyyy", Locale.UK)
    return df.format(Date(this * 1_000L)).toInt()
}
