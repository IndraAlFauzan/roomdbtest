package com.example.testroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kontak")
data class Kontak(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val nomor: String
)
