 package com.example.intelligent_shopping_cart.utils

fun String.isValidMobile(): Boolean {
    val reg = "^1[3|4|5|6|7|8|9][0-9]{9}$"
    return this.matches(reg.toRegex())
}

fun String.isValidEmail(): Boolean {
    val reg = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+\$"
    return this.matches(reg.toRegex())
}