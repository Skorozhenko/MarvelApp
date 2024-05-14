package com.example.marvelapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelapp.data.model.entity.HeroEntity

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(heroes: HeroEntity)

    @Query("SELECT * FROM HeroEntity")
    suspend fun getAllHeroes() : List<HeroEntity>

    @Query("SELECT * FROM HeroEntity WHERE id = :id")
    suspend fun heroById(id: Int) : HeroEntity
}