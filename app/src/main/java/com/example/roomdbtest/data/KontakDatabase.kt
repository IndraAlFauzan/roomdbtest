package com.example.testroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Kontak::class], version = 1, exportSchema = false)
abstract class KontakDatabase : RoomDatabase() {

    abstract fun kontakDao(): KontakDao

    companion object {
        @Volatile
        private var Instance: KontakDatabase? = null

        fun getDatabase(context: Context): KontakDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    KontakDatabase::class.java,
                    "kontak_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}