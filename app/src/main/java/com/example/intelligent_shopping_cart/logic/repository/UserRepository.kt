package com.example.intelligent_shopping_cart.logic.repository

import com.example.intelligent_shopping_cart.logic.dao.UserDao
import com.example.intelligent_shopping_cart.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAll()
    }

    fun getUserById(id: Int): User? {
        return userDao.getById(id)
    }

    fun insertUser(user: User) {
        userDao.insert(user)
    }

    fun deleteUser(user: User) {
        userDao.delete(user)
    }

    suspend fun insertAllUsers(users: List<User>) {
        userDao.insertAll(users)
    }
}