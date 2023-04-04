package com.example.intelligent_shopping_cart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.intelligent_shopping_cart.R

//@Stable
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "avatar_res") val avatarRes: Int = R.drawable.ava1,
    val nickname: String = "nickname null",
    val motto: String = "motto null",
    val gender: String = "gender null",
    var age: Int = 0,
    val phone: String = "phone null",
    val email: String = "email null",
    val password: String = "password null"
)


