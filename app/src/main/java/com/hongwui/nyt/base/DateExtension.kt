package com.hongwui.nyt.base

import java.text.SimpleDateFormat

fun String.fromDateToDate(inFormat: String, outFormat: String): String {
    val fromDate = SimpleDateFormat(inFormat).parse(this)
    return SimpleDateFormat(outFormat).format(fromDate)
}