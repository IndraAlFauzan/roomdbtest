package com.example.roomdbtest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testroom.data.Kontak
import com.example.testroom.data.KontakRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val kontakRepository: KontakRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT = 5000L
    }

    val homeUiState: StateFlow<HomeUiState> =
        kontakRepository.getAllKontak().map { HomeUiState(it) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT),
            initialValue = HomeUiState()
        )

    suspend fun deleteKontak(kontak: Kontak) {
        kontakRepository.deleteKontak(kontak)
    }


}

data class HomeUiState(val itemList: List<Kontak> = listOf())