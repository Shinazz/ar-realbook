package com.shinaz.newapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shinaz.newapp.model.ar.UserData
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM shinaz_table ORDER BY userId ASC")
    fun getAllUserData(): List<UserData>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: UserData)

    @Query("DELETE FROM shinaz_table")
    fun deleteAll()



}