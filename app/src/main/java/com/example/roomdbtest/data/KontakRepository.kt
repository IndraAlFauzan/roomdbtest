package com.example.testroom.data

import kotlinx.coroutines.flow.Flow

interface KontakRepository {

    fun getAllKontak(): Flow<List<Kontak>>

    suspend fun insertKontak(kontak: Kontak)

    suspend fun deleteKontak(kontak: Kontak)

    fun getKontak(id: Int): Flow<Kontak?>

    suspend fun updateKontak(kontak: Kontak)
}