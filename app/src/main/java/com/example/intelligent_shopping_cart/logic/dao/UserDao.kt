package com.example.intelligent_shopping_cart.logic.dao

import androidx.room.*
import com.example.intelligent_shopping_cart.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getById(id: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Insert
    suspend fun insertAll(users: List<User>)
}