package fi.oamk.livecurrency.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fi.oamk.livecurrency.ui.theme.LiveCurrencyTheme
import fi.oamk.livecurrency.viewmodel.CurrencyViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveCurrencyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CurrencyScreen(viewModel, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel, modifier: Modifier = Modifier) {
    val exchangeRates by viewModel.exchangeRates.observeAsState(initial = emptyMap())
//    val date by viewModel.date.observeAsState(initial = "Loading...")
    val isLoading by viewModel.isLoading.observeAsState(initial = true)

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Live EUR Exchange Rates", style = MaterialTheme.typography.headlineMedium)

//        Text(text = "Date: $date", style = MaterialTheme.typography.bodyLarge)


        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            // loading...
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            // exchange rates lists
            LazyColumn {
                items(exchangeRates.entries.toList()) { (currency, rate) ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
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
