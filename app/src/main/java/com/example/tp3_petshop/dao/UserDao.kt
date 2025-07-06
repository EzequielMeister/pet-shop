package com.example.tp3_petshop.dao

import androidx.room.Dao
import androidx.room.*
import com.example.tp3_petshop.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): User?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>
}