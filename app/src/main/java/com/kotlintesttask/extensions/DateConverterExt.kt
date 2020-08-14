package com.kotlintesttask.extensions

import android.annotation.SuppressLint
import android.text.format.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun simpleDateFormat(format: String, dateStr: String): String {
    val date = SimpleDateFormat(format).parse(dateStr)
    val sdf = SimpleDateFormat(PATTERN_DD_MM_YYYY_DOT)
    return sdf.format(date)
}

@SuppressLint("SimpleDateFormat")
fun simpleDateFormat(dateStr: String): Date =
    SimpleDateFormat(PATTERN_DD_MM_YYYY_DOT).parse(dateStr)


@SuppressLint("SimpleDateFormat")
fun isValidFormat(format: String, value: String): Boolean {
    var date: Date? = null
    try {
        val sdf = SimpleDateFormat(format)
        date = sdf.parse(value)
        if (value != sdf.format(date)) {
            date = null
        }
    } catch (ex: ParseException) {
        ex.printStackTrace()
    }
    return date != null
}

fun getAge(dateStr: String): String {
    val date = simpleDateFormat(dateStr)
    val year = DateFormat.format("yyyy", date) as String
    val month =
        DateFormat.format("MM", date) as String // 06
    val day =
        DateFormat.format("dd", date) as String // 20

    val dob = Calendar.getInstance()
    val today = Calendar.getInstance()

    dob[year.toInt(), month.toInt()] = day.toInt()

    var age = today[Calendar.YEAR] - dob[Calendar.YEAR]

    if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
        age--
    }

    val ageInt = age

    return ageInt.toString()
}