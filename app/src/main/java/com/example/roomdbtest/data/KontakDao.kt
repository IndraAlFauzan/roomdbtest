package com.example.testroom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface KontakDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(kontak: Kontak)

    @Query("SELECT * from kontak ORDER BY nama ASC")
    fun getAllKontak(): Flow<List<Kontak>>

    @Query("SELECT * from kontak WHERE id = :id")
    fun getKontak(id: Int): Flow<Kontak>

    @Update
    suspend fun update(kontak: Kontak)

    @Delete()
    suspend fun delete(kontak: Kontak)


}