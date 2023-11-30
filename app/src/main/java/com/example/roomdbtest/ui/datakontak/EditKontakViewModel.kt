package com.example.roomdbtest.ui.datakontak

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testroom.data.KontakRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditKontakViewModel(
    savedStateHandle: SavedStateHandle,
    private val kontakRepository: KontakRepository,
    ) : ViewModel() {

    var editUiState by mutableStateOf(InsertUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[EditKontakDestination.itemIdArg])

    init {
        viewModelScope.launch {
            editUiState = kontakRepository.getKontak(itemId)
                .filterNotNull()
                .first()
                .toUiState()
        }
    }

    suspend fun updateKontak() {
        kontakRepository.updateKontak(editUiState.insertKontakState.toKontak())
    }

    fun updateUIState(state: KontakDetails) {
        editUiState = InsertUiState(insertKontakState = state)
    }


}