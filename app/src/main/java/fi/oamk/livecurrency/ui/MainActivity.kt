package fi.oamk.livecurrency.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fi.oamk.livecurrency.ui.theme.LiveCurrencyTheme
import fi.oamk.livecurrency.utils.Resource
import fi.oamk.livecurrency.viewmodel.CurrencyViewModel
import androidx.activity.viewModels
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

class MainActivity : ComponentActivity() {

    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveCurrencyTheme {
                CurrencyScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel) {
    // State for input and result
    var amount by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("Enter amount to convert") }
    var isLoading by remember { mutableStateOf(false) }

    // Observe ViewModel state
//    viewModel.exchangeResult.observeAsState().value?.let { resultState ->
//        when (resultState) {
//            is Resource.Loading -> {
//                isLoading = true
//                result = "Loading..."
//            }
//            is Resource.Success -> {
//                isLoading = false
//                result = "Converted: ${resultState.data} EUR"
//            }
//            is Resource.Error -> {
//                isLoading = false
//                result = "Error: ${resultState.message}"
//            }
//        }
//    }

    // UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount in USD") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val amountDouble = amount.toDoubleOrNull()
                if (amountDouble != null) {
                    viewModel.convertCurrency("wVan8DU7STKQcyrBz3iRQqryMhahkV", "USD", "EUR", amountDouble)
                } else {
                    result = "Enter a valid amount"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convert")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            Text(
                text = result,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LiveCurrencyTheme {
        CurrencyScreen(viewModel = CurrencyViewModel())
    }
}
