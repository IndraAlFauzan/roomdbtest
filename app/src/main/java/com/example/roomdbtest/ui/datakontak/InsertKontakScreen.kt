package com.example.roomdbtest.ui.datakontak

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdbtest.ui.AppViewModel
import com.example.roomdbtest.ui.navigator.NavigationDestination
import kotlinx.coroutines.launch

object InsertKontakDestination : NavigationDestination {
    override val route = "insert_kontak"
    override val titleRes = "Insert"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertKontakScreen(
    onNavigateBack: () -> Unit,
    viewModel: InsertKontakViewModel = viewModel(factory = AppViewModel.Factory)
) {
    Scaffold { innerPadding ->
        val coroutineScope = rememberCoroutineScope()

        FromKontak(
            insertKontakState = viewModel.insertUiState.insertKontakState,
            onSimpan = {
                coroutineScope.launch {
                    viewModel.insertKontak()
                    onNavigateBack()
                }
            },
            onValueChange = {
                viewModel.updateUIState(it)

            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FromKontak(
    insertKontakState: KontakDetails,
    onSimpan: () -> Unit = {},
    modifier: Modifier,
    onValueChange: (KontakDetails) -> Unit = {}
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = insertKontakState.nama,
            label = { Text(text = "Nama") },
            onValueChange = {
                onValueChange(insertKontakState.copy(nama = it))
            }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            label = { Text(text = "Nomor") },
            value = insertKontakState.nomor,
            onValueChange = {
                onValueChange(insertKontakState.copy(nomor = it))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            onClick = onSimpan,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }


    }
}