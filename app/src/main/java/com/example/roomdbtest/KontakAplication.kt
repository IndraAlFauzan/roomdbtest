package com.example.roomdbtest

import android.app.Application
import com.example.testroom.data.AppContainer
import com.example.testroom.data.AppDataContainer

class KontakAplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}