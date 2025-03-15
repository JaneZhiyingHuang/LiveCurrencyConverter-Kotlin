package fi.oamk.livecurrency.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fi.oamk.livecurrency.viewmodel.CurrencyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel, navController: NavController) {
    val exchangeRates by viewModel.exchangeRates.observeAsState(initial = emptyMap())
    val isLoading by viewModel.isLoading.observeAsState(initial = true)
    val errorMessage by viewModel.errorMessage.observeAsState()

    //navigate to ErrorScreen if error found
    errorMessage?.let { error ->
        LaunchedEffect(error) {
            navController.navigate("errorScreen/$error")
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(
            title = { Text(text = "Live EUR Exchange Rates") },
            actions = {
                IconButton(onClick = { navController.navigate("infoScreen") }) {
                    Icon(Icons.Default.Info, contentDescription = "Info")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            LoadingScreen()
        } else {
            LazyColumn {
                items(exchangeRates.entries.toList()) { (currency, rate) ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "1 EUR  =  ")
                            Text(text = if (rate == -1.0) "Error" else "%.4f".format(rate))
                            Text(text = " $currency")
                        }
                    }
                }
            }
        }
    }
}
