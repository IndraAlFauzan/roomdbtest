package com.example.roomdbtest.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdbtest.ui.AppViewModel
import com.example.roomdbtest.ui.navigator.NavigationDestination
import com.example.testroom.data.Kontak
import kotlinx.coroutines.launch

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = "Home"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,

    viewModel: HomeViewModel = viewModel(factory = AppViewModel.Factory)
) {
    val item by viewModel.homeUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = navigateToItemEntry,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Item"
            )
        }
    }) { innerPadding ->
        HomeBody(
            item = item.itemList,
            navigateToItemUpdate = { navigateToItemUpdate(it.id)
                                   print("id = ${it.id}")
            },
            ondelete = {
                coroutineScope.launch {
                    viewModel.deleteKontak(it)

                }
            },
            modifier = Modifier.padding(innerPadding)
        )

    }


}

@Composable
fun HomeBody(
    item: List<Kontak>,
    navigateToItemUpdate: (Kontak) -> Unit,
    ondelete: (Kontak) -> Unit = {},
    modifier: Modifier = Modifier
) = if (item.isEmpty()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(16.dp)
        )
        Text(
            text = "No Item",
            style = MaterialTheme.typography.titleLarge
        )

    }
} else {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(items = item, key = { it.id }) { item ->
            Card(
                modifier = Modifier
                    .clickable { navigateToItemUpdate(item) }
                    .padding(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = item.nama,
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = item.nomor,
                            style = MaterialTheme.typography.titleMedium
                        )

                    }

                    IconButton(
                        onClick = {
                            ondelete(item)
                        },
                        modifier = Modifier.padding(10.dp)

                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Item",
                        )
                    }
                }
            }

        }
    }

}