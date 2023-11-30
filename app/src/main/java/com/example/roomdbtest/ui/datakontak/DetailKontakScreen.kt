package com.example.roomdbtest.ui.datakontak

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdbtest.ui.AppViewModel
import com.example.roomdbtest.ui.navigator.NavigationDestination


object ItemDetailsDestination : NavigationDestination {
    override val route = "item_details"
    override val titleRes = "Detail Kontak"
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKontakScreen(
    viewModel: DetailKontakViewModel = viewModel(factory = AppViewModel.Factory),
    navigateToEditItem: (Int) -> Unit,
    onBack: () -> Unit

) {
    val item by viewModel.detailUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = ItemDetailsDestination.route) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }

    ) { innerPadding ->

        ItemDetailsBody(
            detailUiState = item,
            onEdit = {
                navigateToEditItem(item.detailKontakState.id)
            },
            modifier = Modifier.padding(innerPadding),
            onBack = onBack
        )
    }
}

@Composable
private fun ItemDetailsBody(
    detailUiState: DetailUiState,
    onEdit: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Card(
            modifier = Modifier, colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ItemDetailsRow(
                    labelResID = "Nama",
                    itemDetail = detailUiState.detailKontakState.nama,
                    modifier = Modifier.padding(
                        horizontal = 9.dp
                    )
                )
                ItemDetailsRow(
                    labelResID = "No Telepon",
                    itemDetail = detailUiState.detailKontakState.nomor,
                    modifier = Modifier.padding(
                        horizontal = 9.dp
                    )
                )


            }

        }
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Back")
        }


        Button(
            onClick = onEdit,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
        ) {
            Text("Edit")
        }


    }
}


@Composable
private fun ItemDetailsRow(
    labelResID: String, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = labelResID)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}