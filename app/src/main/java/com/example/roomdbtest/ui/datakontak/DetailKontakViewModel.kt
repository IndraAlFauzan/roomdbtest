package com.example.roomdbtest.ui.datakontak

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testroom.data.KontakRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class DetailKontakViewModel(
    savedStateHandle: SavedStateHandle,
    private val kontakRepository: KontakRepository,
) : ViewModel() {

    /**
     * Holds the item details ui state. The data is retrieved from [ItemsRepository] and mapped to
     * the UI state.
     */

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])


    val detailUiState: StateFlow<DetailUiState> = kontakRepository.getKontak(itemId)
        .filterNotNull()
        .map { DetailUiState(detailKontakState = it.toKontakDetails()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = DetailUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


}

data class DetailUiState(
    val detailKontakState: KontakDetails = KontakDetails(),
)

