package com.kotlintesttask.extensions

import android.view.View

fun View.showLoading(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun String.capitalizeFirstLetter() =
    this.substring(0, 1).toUpperCase() + this.substring(1).toLowerCase()

