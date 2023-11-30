package com.example.testroom.data

import kotlinx.coroutines.flow.Flow

class OfflineKontakRepository(private val kontakDao: KontakDao) : KontakRepository {

    override fun getAllKontak(): Flow<List<Kontak>> {
        return kontakDao.getAllKontak()
    }

    override suspend fun insertKontak(kontak: Kontak) {
        kontakDao.insert(kontak)
    }

    override suspend fun deleteKontak(kontak: Kontak) {
        kontakDao.delete(kontak)
    }

    override fun getKontak(id: Int): Flow<Kontak?> = kontakDao.getKontak(id)


    override suspend fun updateKontak(kontak: Kontak) {
        kontakDao.update(kontak)
    }
}