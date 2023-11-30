package com.example.roomdbtest.ui.datakontak

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdbtest.ui.AppViewModel
import com.example.roomdbtest.ui.navigator.NavigationDestination
import kotlinx.coroutines.launch


object EditKontakDestination : NavigationDestination {
    override val route = "edit_kontak"
    override val titleRes = "Edit"
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditKontakScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditKontakViewModel = viewModel(factory = AppViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(

    ) { innerPadding ->
        FromKontak(
            insertKontakState = viewModel.editUiState.insertKontakState,
            onValueChange = viewModel::updateUIState,
            onSimpan = {
                coroutineScope.launch {
                    viewModel.updateKontak()
                    navigateBack()
                    onNavigateUp()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
