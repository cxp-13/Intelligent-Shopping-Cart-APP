package com.example.intelligent_shopping_cart.logic.repository

import com.example.intelligent_shopping_cart.logic.dao.UserDao
import com.example.intelligent_shopping_cart.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun getAllUsers(): Flow<List<User>> = withContext(Dispatchers.IO) {
        userDao.getAll()
    }

    suspend fun getUserById(id: Int): Flow<User> = withContext(Dispatchers.IO) {
        userDao.getById(id)
    }

    suspend fun insertUser(user: User) = withContext(Dispatchers.IO) {
        userDao.insert(user)
    }

    suspend fun deleteUser(user: User) = withContext(Dispatchers.IO) {
        userDao.delete(user)
    }

    suspend fun insertAllUsers(users: List<User>) = withContext(Dispatchers.IO) {
        userDao.insertAll(users)
    }
}