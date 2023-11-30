package com.example.roomdbtest.ui.datakontak

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.testroom.data.Kontak
import com.example.testroom.data.KontakRepository

class InsertKontakViewModel(private val kontakRepository: KontakRepository) : ViewModel() {

    var insertUiState by mutableStateOf(InsertUiState())
        private set

    suspend fun insertKontak() {
        kontakRepository.insertKontak( insertUiState.insertKontakState.toKontak())
    }

    fun updateUIState(state: KontakDetails) {
        insertUiState = InsertUiState(insertKontakState = state)
    }

}

data class InsertUiState(
   val insertKontakState: KontakDetails = KontakDetails(),
)

data class KontakDetails(
    val id : Int = 0,
    val nama: String = "",
    val nomor: String = "",
)

fun KontakDetails.toKontak(): Kontak {
    return Kontak(
        id = id,
        nama = nama,
        nomor = nomor
    )
}

fun Kontak.toUiState(): InsertUiState = InsertUiState(
    insertKontakState = this.toKontakDetails()
)

fun Kontak.toKontakDetails(): KontakDetails = KontakDetails(
    id = id,
    nama = nama,
    nomor = nomor
)