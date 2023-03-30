package com.example.intelligent_shopping_cart.model

import com.example.intelligent_shopping_cart.R
import java.util.*

//@Stable
data class User(
    val avatarRes: Int = R.drawable.ava1,
    val nickname: String,
    val motto: String = "",
    val gender: String? = null,
    val age: Int? = null,
    val phone: String? = null,
    val email: String? = null,
    val uid: String = UUID.randomUUID().toString(),
    val password: String? = null
)


