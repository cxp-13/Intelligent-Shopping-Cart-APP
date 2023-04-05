package com.example.intelligent_shopping_cart.logic.utils

import com.example.intelligent_shopping_cart.model.User
import java.util.*

object UserFactory {

    private val RANDOM = Random()

    private val nicknames = arrayOf("Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace")
    private val mottos = arrayOf(
        "Just do it",
        "Stay hungry, stay foolish",
        "Think different",
        "Impossible is nothing",
        "Be yourself"
    )
    private val genders = arrayOf("Male", "Female", "Other")
    private val phones =
        arrayOf("13800001234", "13911112222", "15012345678", "15888889999", "17766667777")

    fun createDummyUsers(count: Int): List<User> {
        return (1..count).map { index ->
            val nickname = nicknames.random() + index.toString()
            val avatar = "https://picsum.photos/id/${RANDOM.nextInt(1000)}/200/300"
            val motto = mottos.random()
            val gender = genders.random()
            val age = Random().nextInt(80) + 18
            val phone = phones.random()
            val email = "$nickname@example.com"
            val password = UUID.randomUUID().toString().replace("-", "").substring(0, 8)
            User(id = 0, avatar, nickname, motto, gender, age, phone, email, password)
        }
    }
}