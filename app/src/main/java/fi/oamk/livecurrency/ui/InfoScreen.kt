package fi.oamk.livecurrency.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(
            title = { Text(text = "Info") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = """
        This app provides real-time USD currency exchange rates.
        App uses ExchangeRate API available on https://api.currencyfreaks.com/v2.0/
        Developed by Zhiying Huang
    """.trimIndent()
        )

    }
}
