package com.isaev.common

fun Int.formatPrice(): String {
    val str = this.toString()
    val n = str.length

    val sb = StringBuilder()

    for (i in str.indices) {
        sb.append(str[n - 1 - i])
        if ((i + 1) % 3 == 0) {
            sb.append(' ')
        }
    }

    return sb.reversed().toString()
}