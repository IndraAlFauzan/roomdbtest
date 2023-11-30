package com.example.roomdbtest.ui


import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomdbtest.KontakAplication
import com.example.roomdbtest.ui.datakontak.DetailKontakViewModel
import com.example.roomdbtest.ui.datakontak.EditKontakViewModel
import com.example.roomdbtest.ui.datakontak.InsertKontakViewModel
import com.example.roomdbtest.ui.home.HomeViewModel


object AppViewModel {
    val Factory = viewModelFactory {
        initializer {
            InsertKontakViewModel(
                kontakAplication().container.kontakRepository
            )
        }

        initializer {
            HomeViewModel(
                kontakAplication().container.kontakRepository
            )
        }

        initializer {
            DetailKontakViewModel(
                this.createSavedStateHandle(),
                kontakAplication().container.kontakRepository,
            )
        }

        initializer {
            EditKontakViewModel(
                this.createSavedStateHandle(),
                kontakAplication().container.kontakRepository,
            )

        }

    }
}

fun CreationExtras.kontakAplication(): KontakAplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as KontakAplication)