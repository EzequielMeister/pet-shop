package com.example.tp3_petshop.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp3_petshop.dao.FavoriteProductDao
import com.example.tp3_petshop.dao.UserDao
import com.example.tp3_petshop.models.FavoriteProduct
import com.example.tp3_petshop.models.User

@Database(entities = [FavoriteProduct::class, User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteProductDao(): FavoriteProductDao
    abstract fun userDao(): UserDao;
}