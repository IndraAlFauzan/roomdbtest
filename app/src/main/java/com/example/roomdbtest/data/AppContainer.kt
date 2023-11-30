package com.example.testroom.data

import android.content.Context

interface AppContainer {

    val kontakRepository: KontakRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val kontakRepository: KontakRepository by lazy {
        OfflineKontakRepository(KontakDatabase.getDatabase(context).kontakDao())
    }

}