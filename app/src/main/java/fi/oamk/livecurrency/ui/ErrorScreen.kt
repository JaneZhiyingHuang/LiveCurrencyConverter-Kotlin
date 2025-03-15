package fi.oamk.livecurrency.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ErrorScreen(navController: NavController, errorMessage: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = errorMessage, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = { navController.navigate("currencyScreen") }) {
//            Text("Retry")
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(navController = rememberNavController(), errorMessage = "Network connection lost. Please try again.")
}