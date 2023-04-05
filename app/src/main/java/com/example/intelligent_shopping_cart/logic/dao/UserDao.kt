package com.example.intelligent_shopping_cart.logic.dao

import androidx.room.*
import com.example.intelligent_shopping_cart.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getById(id: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Insert
    suspend fun insertAll(users: List<User>)
}